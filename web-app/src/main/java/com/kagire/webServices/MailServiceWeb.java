package com.kagire.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class MailServiceWeb {
    private final static String URL = String.format("%s://%s:%d", "http", "localhost", 8081);

    private final RestTemplate restTemplate;

    @Autowired
    public MailServiceWeb(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendInfoEmail(String emailAddress){
        System.out.println(emailAddress);
        restTemplate.postForEntity(URL + "/mail/info", emailAddress, String.class);
    }
}
