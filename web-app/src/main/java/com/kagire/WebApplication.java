package com.kagire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource("application.properties"),
        @PropertySource("classpath:db.properties")
})
public class WebApplication {
    public static void main(String[] args){
        SpringApplication.run(WebApplication.class, args);
    }
}
