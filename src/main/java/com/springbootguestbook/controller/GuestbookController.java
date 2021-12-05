package com.springbootguestbook.controller;

import com.springbootguestbook.dto.PageRequestDTO;
import com.springbootguestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestBookService service;

    @GetMapping({"","/"})
    public String list() {
        log.info("list...");

        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model md) {

        log.info("list......" + pageRequestDTO);

        md.addAttribute("result", service.getList(pageRequestDTO));
    }
}
