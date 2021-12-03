package com.springbootguestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan("com.springbootguestbook.*")

// BaseEntity 의 AuditingEntityListener 를 활성화 하기 위한 Annotation
@EnableJpaAuditing
public class SpringBootGuestBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGuestBookApplication.class, args);
        System.out.println("Start");
    }
}
