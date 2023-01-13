package com.fh.vs.gruppe1.bank.service;

import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.repository.CustomerRepository;
import com.fh.vs.gruppe1.external.tradingservice.TradingServiceClient;
import com.fh.vs.gruppe1.external.tradingservice.tmp.BuyResponse;
import com.fh.vs.gruppe1.external.tradingservice.tmp.FindStockQuotesByCompanyNameResponse;
import com.fh.vs.gruppe1.external.tradingservice.tmp.GetStockQuotesResponse;
import com.fh.vs.gruppe1.external.tradingservice.tmp.PublicStockQuote;
import com.fh.vs.gruppe1.transaction.ClientOrder;
import com.fh.vs.gruppe1.transaction.ClientOrderRepository;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankService {

    @Autowired
    TradingServiceClient tradingServiceClient;


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    ClientOrderRepository clientOrderRepository;

    public List<PublicStockQuote> getStocksByNameOrSymbol(String search){

        FindStockQuotesByCompanyNameResponse resName = tradingServiceClient.getStockQuotebyCompanyName(search).getValue();
        return resName.getReturn();
    }

    public double getCurrentShareValue(String symbol){
        GetStockQuotesResponse currentStockQuote = tradingServiceClient.getStockQuotes(symbol).getValue();
        BigDecimal currentPrice = currentStockQuote.getReturn().get(0).getLastTradePrice();
        return currentPrice.doubleValue();
    }

    public ClientOrder buyStock(String symbol, Integer amount, String userEmail){
        Optional<Customer> customer = customerRepository.findByEmail(userEmail);

        if(customer.isEmpty()){
            log.error("Customer not found");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("No customer found")
            );
        }
        PublicStockQuote stock = tradingServiceClient.getStockQuotes(symbol).getValue().getReturn().get(0);;

        Optional<Bank> bank = bankRepository.findById(1l);
        if(bank.isEmpty()){
            log.error("Bank vol not found");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Bank vol not found, should not happen")
            );
        }

        Bank tmpBank = bank.get();
        Double orderVol = stock.getLastTradePrice().doubleValue() * amount;

        if(orderVol > tmpBank.getTotalOrderVolume()){
            log.error("Bank vol not enough");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Bank volume is not high enough, or not enough shares available"));
        }
        if(amount > stock.getFloatShares()){
            log.error("Not enough shares available");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Not enough shares available"));
        }
        //buy share and persist order
        BuyResponse buy = tradingServiceClient.buyShares(symbol, amount).getValue();
        tmpBank.setTotalOrderVolume(tmpBank.getTotalOrderVolume() - orderVol);
        bankRepository.save(tmpBank);

        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setAmount(amount);
        clientOrder.setDepot(customer.get().getDepot());
        clientOrder.setSymbol(symbol);
        clientOrder.setCompanyName(stock.getCompanyName());
        clientOrder.setUnitPrice(stock.getLastTradePrice().doubleValue());
        clientOrderRepository.save(clientOrder);
        clientOrder.setCurrentPrice(stock.getLastTradePrice().doubleValue());

        return clientOrder;

    }

    public double getStockBySymbol(String symbol){



        return 0.00;
    }

}
