package com.kagire.controllers;

import com.kagire.MailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MailerRestController.class)
class MailerRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MailServiceImpl mailServiceImpl;

    @Test
    void sendInfoMail() throws Exception {
        this.mvc.perform(post("/mail/info")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("testkagire@com"))
                .andExpect(status().isOk())
                .andExpect(content().string("Sent successfully"));
    }
}