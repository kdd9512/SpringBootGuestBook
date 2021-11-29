package com.springbootguestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.springbootguestbook.controller")
public class SpringBootGuestBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGuestBookApplication.class, args);
        System.out.println("Start");
    }
}
