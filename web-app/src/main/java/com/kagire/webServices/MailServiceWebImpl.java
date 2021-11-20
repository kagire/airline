package com.kagire.webServices;

import com.kagire.MailService;
import com.kagire.config.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

public class MailServiceWebImpl implements MailService {

    static Properties configuration;

    static {
        try {
            configuration = PropertiesLoader.loadProperties("application.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String localAddress = configuration.getProperty("host.address");

    private final static String URL = String.format("%s://%s:%d", "http", localAddress, 8081);

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
