package com.fh.vs.gruppe1.bank.service;

import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.repository.CustomerRepository;
import com.fh.vs.gruppe1.depot.DepotRepository;
import com.fh.vs.gruppe1.external.tradingservice.TradingServiceClient;
import com.fh.vs.gruppe1.external.tradingservice.tmp.BuyResponse;
import com.fh.vs.gruppe1.external.tradingservice.tmp.SellResponse;
import com.fh.vs.gruppe1.external.tradingservice.tmp.FindStockQuotesByCompanyNameResponse;
import com.fh.vs.gruppe1.external.tradingservice.tmp.GetStockQuotesResponse;
import com.fh.vs.gruppe1.external.tradingservice.tmp.PublicStockQuote;
import com.fh.vs.gruppe1.transaction.ClientOrder;
import com.fh.vs.gruppe1.transaction.ClientOrderRepository;
import com.fh.vs.gruppe1.transaction.ClientOrderService;
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

    @Autowired
    ClientOrderService coservice;

    @Autowired
    DepotRepository depotRepository;

    public List<PublicStockQuote> getStocksByNameOrSymbol(String search){

        FindStockQuotesByCompanyNameResponse resName = tradingServiceClient.getStockQuotebyCompanyName(search).getValue();
        return resName.getReturn();
    }

    public double getCurrentShareValue(String symbol){
        GetStockQuotesResponse currentStockQuote = tradingServiceClient.getStockQuotes(symbol).getValue();
        BigDecimal currentPrice = currentStockQuote.getReturn().get(0).getLastTradePrice();
        return currentPrice.doubleValue();
    }

    public boolean  sellStock(String symbol, Integer amount, String userEmail, Long transaction){
        Optional<Customer> customer = customerRepository.findByEmail(userEmail);
        //check if  customer exists
        if(customer.isEmpty()){
            log.error("Customer not found");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("No customer found")
            );
        }
        System.out.println(customer.get());
        System.out.println(customer.get().getDepot());
        System.out.println(customer.get().getDepot().getId());
        System.out.println(customer.get().getDepot().getTransactions());
        //Check if the customer has enough shares to sell
        List<ClientOrder> sharesOwned = customer.get().getDepot().getTransactions();
       //check if shares exists
        if(sharesOwned.isEmpty()){
            log.error("Shares not found");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Shares not found")
            );
        }


        //check if transaction id belongs to customer Depot
        Customer custobj = customerRepository.findByEmail(userEmail).get();
        List<ClientOrder> coobj = custobj.getDepot().getTransactions();
        ClientOrder ourco = null;
        boolean coexists = false;
        for(ClientOrder co : coobj){

            if (co.getId() == transaction){
                coexists = true;
                ourco = co;
            }
        }
        if (!coexists){
            log.error("permission denied for transaction");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("permission denied for transaction")
            );
        }

        if (amount<ourco.getAmount()){

            ourco.setAmount(ourco.getAmount()-amount);
            clientOrderRepository.save(ourco);

        }
        else if (amount==ourco.getAmount()){
            clientOrderRepository.deleteById(ourco.getId());
        }



        //get stock from trading service (froihofer)
        PublicStockQuote stock = tradingServiceClient.getStockQuotes(symbol).getValue().getReturn().get(0);
        //check if Bank exists
        Optional<Bank> bank = bankRepository.findById(1l);
        if(bank.isEmpty()){
            log.error("Bank not found");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Bank not found, should not happen")
            );
        }

        Bank tmpBank = bank.get();
        Double orderVol = stock.getLastTradePrice().doubleValue() * amount;
        tmpBank.setTotalOrderVolume(tmpBank.getTotalOrderVolume() + orderVol);
        bankRepository.save(tmpBank);

        SellResponse sell = tradingServiceClient.sellShares(symbol, amount).getValue();
        return true;

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
