package com.resttemplate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.web.client.RestTemplate;

public class Application {

    private static final Log log = LogFactoryImpl.getLog(Application.class);

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", String.class);
        log.info(quote.toString());
    }

}