package com.fh.vs.gruppe1.external.tradingservice;


import com.fh.vs.gruppe1.external.tradingservice.tmp.FindStockQuotesByCompanyName;
import com.fh.vs.gruppe1.external.tradingservice.tmp.FindStockQuotesByCompanyNameResponse;
import com.fh.vs.gruppe1.external.tradingservice.tmp.ObjectFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;


//URL spring.io/guides/gs/consuming-web-service
@Slf4j
public class TradingServiceClient extends WebServiceGatewaySupport {

    public JAXBElement<FindStockQuotesByCompanyNameResponse> getStockQuotebyCompanyName(String name) {

        ObjectFactory objectFactory = new ObjectFactory();

        FindStockQuotesByCompanyName req = objectFactory.createFindStockQuotesByCompanyName();
        req.setPartOfCompanyName(name);

        JAXBElement<FindStockQuotesByCompanyName> jaxbElement = objectFactory.createFindStockQuotesByCompanyName(req);
        log.info(jaxbElement+ " FIIIIIIIIIIIIIIIIIIIIIREEEEEEEEEEEEEEEEEEEEEEEEDDD ");

        return (JAXBElement<FindStockQuotesByCompanyNameResponse>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
    }


}
