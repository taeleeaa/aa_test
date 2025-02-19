package com.test.architect_prj.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class PageController {
    @GetMapping("/")
    public String homePage() {
        log.debug("home.jsp로 포워딩!");
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        log.debug("login.jsp로 포워딩!");
        return "login";
    }

    @GetMapping("/accessError")
    public String accessError() {
        log.debug("accessError.jsp로 포워딩!");
        return "accessError";
    }

    @GetMapping("/auth")
    public String authPage() {
        log.debug("auth.jsp로 포워딩!");
        return "auth";
    }


    @GetMapping("/ceo")
    public String e7ePage() {
        log.debug("ceo.jsp로 포워딩!");
        return "ceo";
    }

    @GetMapping("/manager")
    public String managerPage() {
        log.debug("manager.jsp로 포워딩!");
        return "manager";
    }


    @GetMapping("/user")
    public String userPage() {
        log.debug("user.jsp로 포워딩!");
        return "user";
    }

    @GetMapping("/alba")
    public String albaPage() {
        log.debug("alba.jsp로 포워딩!");
        return "alba";
    }
}
