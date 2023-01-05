package com.fh.vs.gruppe1.bank.service;

import com.fh.vs.gruppe1.external.tradingservice.TradingServiceClient;
import com.fh.vs.gruppe1.external.tradingservice.tmp.FindStockQuotesByCompanyNameResponse;
import com.fh.vs.gruppe1.external.tradingservice.tmp.GetStockQuotesResponse;
import com.fh.vs.gruppe1.external.tradingservice.tmp.PublicStockQuote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankService {

    @Autowired
    TradingServiceClient tradingServiceClient;


    public List<PublicStockQuote> getStocksByNameOrSymbol(String search){

        FindStockQuotesByCompanyNameResponse resName = tradingServiceClient.getStockQuotebyCompanyName(search).getValue();
        return resName.getReturn();
    }

    public double getCurrentShareValue(String symbol){
        GetStockQuotesResponse currentStockQuote = tradingServiceClient.getStockQuotes(symbol).getValue();
        BigDecimal currentPrice = currentStockQuote.getReturn().get(0).getLastTradePrice();
        return currentPrice.doubleValue();
    }

    public double getStockBySymbol(String symbol){



        return 0.00;
    }

}
