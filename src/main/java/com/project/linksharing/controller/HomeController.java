package com.project.linksharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    @GetMapping(value="/home")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

}
