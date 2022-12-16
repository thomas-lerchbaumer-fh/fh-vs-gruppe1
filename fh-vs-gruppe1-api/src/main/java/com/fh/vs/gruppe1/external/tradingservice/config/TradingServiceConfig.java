package com.fh.vs.gruppe1.external.tradingservice.config;

import com.fh.vs.gruppe1.external.tradingservice.TradingServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;


@Slf4j
@Configuration
public class TradingServiceConfig {
    @Value("${ts.name}")
    private String userName;

    @Value("${ts.pw}")
    private String userPassword;

    @Value("http://edu.dedisys.org/ds-finance/ws/TradingService?wsdl")
    private String defaultUri;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.fh.vs.gruppe1.external.tradingservice.tmp");
        return marshaller;
    }

    @Bean
    public TradingServiceClient tradingClient(Jaxb2Marshaller marshaller) {
        TradingServiceClient client = new TradingServiceClient();
        client.setDefaultUri(defaultUri);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        client.setWebServiceTemplate(webServiceTemplate());
        return client;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller());
        webServiceTemplate.setUnmarshaller(marshaller());
        webServiceTemplate.setDefaultUri(defaultUri);
        // set a HttpComponentsMessageSender which provides support for basic authentication
        webServiceTemplate.setMessageSender(httpComponentsMessageSender());

        return webServiceTemplate;
    }

    @Bean
    public HttpComponentsMessageSender httpComponentsMessageSender() {
        HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
        // set the basic authorization credentials
        httpComponentsMessageSender.setCredentials(usernamePasswordCredentials());
        return httpComponentsMessageSender;
    }

    @Bean
    public UsernamePasswordCredentials usernamePasswordCredentials() {
        return new UsernamePasswordCredentials(userName, userPassword);
    }

}
