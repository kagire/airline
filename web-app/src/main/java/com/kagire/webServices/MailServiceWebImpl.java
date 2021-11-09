package com.kagire.webServices;

import com.kagire.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class MailServiceWebImpl implements MailService {
    private final static String URL = String.format("%s://%s:%d", "http", "localhost", 8081);

    private final RestTemplate restTemplate;

    @Autowired
    public MailServiceWebImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendInfoEmail(String emailAddress){
        restTemplate.postForEntity(URL + "/mail/info", emailAddress, String.class);
    }
}
