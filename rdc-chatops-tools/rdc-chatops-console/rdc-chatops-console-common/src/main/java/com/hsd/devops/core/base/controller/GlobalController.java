package com.hsd.devops.core.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/global")
public class GlobalController {

    
    @RequestMapping(path = "/error")
    public String errorPage() {
        return "/404.html";
    }

    
    @RequestMapping(path = "/sessionError")
    public String errorPageInfo(Model model) {
        model.addAttribute("tips", "session超时");
        return "/login.html";
    }
}
