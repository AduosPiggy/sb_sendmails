package com.example.demo_sendmultmails;

import com.example.demo_sendmultmails.service.EmailService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSendMulMailsApplication {
    @Autowired
    EmailService emailService;
    public static void main(String[] args) {
        SpringApplication.run(DemoSendMulMailsApplication.class, args);
    }

    @PostConstruct
    public void run(){
        emailService.run();
    }
}
