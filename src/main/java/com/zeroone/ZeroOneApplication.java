package com.zeroone;

import com.zeroone.configuration.FirstStartup;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ZeroOneApplication {

    private final FirstStartup firstStartup;

    public ZeroOneApplication(FirstStartup firstStartup) {
        this.firstStartup = firstStartup;
    }

    public static void main(String[] args) {

        SpringApplication.run(ZeroOneApplication.class, args);

    }

    @PostConstruct
    public void methodToRunOnStartup() {
        firstStartup.createStartupConfig();
    }

}
