package com.meanmachines.MeanStreamMachine.controller;

import com.meanmachines.MeanStreamMachine.model.dto.response.DetailsDTO;
import com.meanmachines.MeanStreamMachine.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class WebController {
    @Autowired
    private MediaService mediaService;
    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/upload")
    String upload() {
        return "upload";
    }
    @RequestMapping(value = {"/","/home"})
    public String home(Model model) {

        model.addAttribute("allMedia", DetailsDTO.mediaListToDetailDTOList(mediaService.getAllMedia()));
        return "home";
    }

}
