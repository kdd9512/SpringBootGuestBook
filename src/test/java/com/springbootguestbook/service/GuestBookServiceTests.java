package com.springbootguestbook.service;

import com.springbootguestbook.dto.GuestBookDTO;
import com.springbootguestbook.dto.PageRequestDTO;
import com.springbootguestbook.dto.PageResultDTO;
import com.springbootguestbook.entity.GuestBook;
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

//    @Test
//    public void testList() {
//
//        PageRequestDTO pageRequestDTO =
//                PageRequestDTO.builder()
//                .page(1)
//                .size(10)
//                .build();
//
//        PageResultDTO<GuestBookDTO, GuestBook> resultDTO =
//                service.getList(pageRequestDTO);
//
//        for (GuestBookDTO guestBookDTO : resultDTO.getDtoList()) {
//            System.out.println(guestBookDTO);
//        }
//    }

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDTO<GuestBookDTO,GuestBook> resultDTO =
                service.getList(pageRequestDTO);

        System.out.println("PREV : " + resultDTO.isPrev());
        System.out.println("NEXT : " + resultDTO.isNext());
        System.out.println("TOTAL : " + resultDTO.getTotalPage());
        System.out.println("-----------------------------------------------------------");

        for (GuestBookDTO guestBookDTO : resultDTO.getDtoList()) {
            System.out.println(guestBookDTO);
        }

        System.out.println("-------------------------PAGE NUMBER---------------------------");
        resultDTO.getPageList().forEach(System.out::println);
    }
}
