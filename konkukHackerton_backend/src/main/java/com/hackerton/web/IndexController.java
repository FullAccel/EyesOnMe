package com.hackerton.web;

import com.hackerton.security.dto.SessionMember;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

