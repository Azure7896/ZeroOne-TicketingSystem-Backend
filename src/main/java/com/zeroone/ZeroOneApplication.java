package com.zeroone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableAsync
@CrossOrigin(origins = "http://localhost:4200")
public class ZeroOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeroOneApplication.class, args);
    }

}
