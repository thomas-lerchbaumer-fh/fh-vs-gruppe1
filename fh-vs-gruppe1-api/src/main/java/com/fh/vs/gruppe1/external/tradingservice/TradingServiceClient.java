package com.fh.vs.gruppe1.external.tradingservice;


import com.fh.vs.gruppe1.external.tradingservice.tmp.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;


//URL spring.io/guides/gs/consuming-web-service
@Slf4j
public class TradingServiceClient extends WebServiceGatewaySupport {

    public JAXBElement<FindStockQuotesByCompanyNameResponse> getStockQuotebyCompanyName(String search) {

        ObjectFactory objectFactory = new ObjectFactory();

        FindStockQuotesByCompanyName req = objectFactory.createFindStockQuotesByCompanyName();
        req.setPartOfCompanyName(search);

        JAXBElement<FindStockQuotesByCompanyName> jaxbElement = objectFactory.createFindStockQuotesByCompanyName(req);

        return (JAXBElement<FindStockQuotesByCompanyNameResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
    }


    public JAXBElement<GetStockQuotesResponse> getStockQuotes(String name) {

        ObjectFactory objectFactory = new ObjectFactory();

        GetStockQuotes req = objectFactory.createGetStockQuotes();
        req.getSymbols().add(name);

        JAXBElement<GetStockQuotes> jaxbElement = objectFactory.createGetStockQuotes(req);

        return (JAXBElement<GetStockQuotesResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
    }

    public JAXBElement<BuyResponse> buyShares(String symbol, int shares) {
        ObjectFactory objectFactory = new ObjectFactory();

        Buy req = objectFactory.createBuy();
        req.setShares(shares);
        req.setSymbol(symbol);

        JAXBElement<Buy> jaxbElement = objectFactory.createBuy(req);

        return (JAXBElement<BuyResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
    }


}
