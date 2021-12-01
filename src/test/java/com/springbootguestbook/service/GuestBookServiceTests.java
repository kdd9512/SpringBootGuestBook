package com.springbootguestbook.service;

import com.springbootguestbook.dto.GuestBookDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestBookServiceTests {

    @Autowired
    private GuestBookService service;

    @Test
    public void testRegister() {

        GuestBookDTO guestBookDTO = GuestBookDTO.builder()
                .title("Title..")
                .content("Content..")
                .writer("Writer..")
                .build();

        System.out.println(service.register(guestBookDTO));
    }
}
