package com.test.helloworld;

import com.test.helloworld.model.HealthStatus;
import com.test.helloworld.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloWorldService helloWorldService;

    @Test
    public void shouldReturnDefaultHello() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello!")));
    }

    @Test
    public void shouldReturnHealthStatus() throws Exception {
        String curDate = DateTimeFormatter
                .ofPattern("yyyy-MM-dd hh:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now());

        when(helloWorldService.getHealthStatus())
                .thenReturn(new HealthStatus(HttpStatus.OK.name(), "0.0.1", "up since " + curDate));

        this.mockMvc
                .perform(get("/healthz"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"OK\",\"version\":\"0.0.1\",\"uptime\":\"up since " + curDate + "\"}"));
    }

}
