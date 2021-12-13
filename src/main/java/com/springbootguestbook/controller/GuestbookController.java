package com.springbootguestbook.controller;

import com.springbootguestbook.dto.GuestBookDTO;
import com.springbootguestbook.dto.PageRequestDTO;
import com.springbootguestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestBookService service;

    @GetMapping({"", "/"})
    public String list() {
        log.info("list...");

        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list......" + pageRequestDTO);

        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register() {
        log.info("register....");
    }

    @PostMapping("/register")
    public String registerPost(GuestBookDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto..." + dto);

        // 새로 추가된 엔티티의 번호.
        Long gno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg" , gno);

        return "redirect:/guestbook/list";
    }

    @GetMapping("/read")
    public void read(
            long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
            Model model) {

        log.info("gno : " + gno);

        GuestBookDTO dto = service.read(gno);

        model.addAttribute("dto", dto);
    }

}
