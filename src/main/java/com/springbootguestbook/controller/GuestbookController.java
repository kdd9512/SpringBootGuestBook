package com.springbootguestbook.controller;

import com.springbootguestbook.dto.GuestBookDTO;
import com.springbootguestbook.dto.PageRequestDTO;
import com.springbootguestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
// method 를 정의하여 반드시 POST 방식을 사용할 것을 선언해야 한다. 기본값이 GET 이기 때문.
@RequestMapping(value = "/guestbook")
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

    @GetMapping({"/read","/modify"})
    public void read(
            long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
            Model model) {

        log.info("gno : " + gno);

        GuestBookDTO dto = service.read(gno);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(long gno, RedirectAttributes redirectAttributes) {

        log.info("gno : " + gno);
        service.remove(gno);
        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

    @PostMapping("/modify")
    public String modify(GuestBookDTO dto, @ModelAttribute("requestDTO")
            PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        log.info("=============================post modify===================================");
        log.info("dto : " + dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page",requestDTO.getPage());
        redirectAttributes.addAttribute("type",requestDTO.getType());
        redirectAttributes.addAttribute("keyword",requestDTO.getKeyword());
        redirectAttributes.addAttribute("gno", dto.getGno());

        return "redirect:/guestbook/read";
    }
}
