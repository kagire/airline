package com.kagire;

import com.kagire.config.MailConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *  Coverage increase test
 */
@SpringBootTest(classes = MailConfig.class)
public class MailConfigTest {

    @Autowired
    MailConfig mailConfig;

    @Test
    public void isConfigAlrightTest(){
        assert mailConfig != null;
    }
}
