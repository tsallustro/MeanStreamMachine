package com.meanmachines.MeanStreamMachine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping(value = "/")
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/react")
    public String react(){
        return "react-page";
    }
}
