package com.hackerton.domain.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

//    @GetMapping("/")
//    public String index(Model model) {
//        SessionMember user = (SessionMember) httpSession.getAttribute("user");
//        if(user != null){
//            model.addAttribute("userName", user.getName());
//        }
//        return "index";
//    }
}

