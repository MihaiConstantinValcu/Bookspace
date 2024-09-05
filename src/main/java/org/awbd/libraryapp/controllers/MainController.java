package org.awbd.libraryapp.controllers;

import lombok.RequiredArgsConstructor;
import org.awbd.libraryapp.dtos.BookDto;
import org.awbd.libraryapp.dtos.RegisterDto;
import org.awbd.libraryapp.services.BookService;
import org.awbd.libraryapp.services.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;
    private final BookService bookService;

    @RequestMapping({"", "/", "/home"})
    public ModelAndView getHome() {
        Page<BookDto> bookPage = bookService.findPage(PageRequest.of(0, 5)); // Example: provide a default pageable
        ModelAndView mav = new ModelAndView("bookPaged");
        mav.addObject("bookPage", bookPage);
        return mav;
    }

    @GetMapping("/login")
    public String showLogInForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterDto payload) {
        memberService.register(payload);
        return "login";
    }

    @GetMapping("/access_denied")
    public String accessDeniedPage() {
        return "accessDenied";
    }
}
