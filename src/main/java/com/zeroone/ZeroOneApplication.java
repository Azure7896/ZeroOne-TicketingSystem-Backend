package com.zeroone;

import com.zeroone.configuration.HelloMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication (exclude={SecurityAutoConfiguration.class})
@EnableAsync
public class ZeroOneApplication {

    private final HelloMessage helloMessage;

    public ZeroOneApplication(HelloMessage helloMessage) {
        this.helloMessage = helloMessage;
    }

    public static void main(String[] args) {

        SpringApplication.run(ZeroOneApplication.class, args);

    }

    @PostConstruct
    public void methodToRunOnStartup() {
        helloMessage.createHelloTicket();
    }

}
