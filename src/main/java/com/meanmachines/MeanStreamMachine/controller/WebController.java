package com.meanmachines.MeanStreamMachine.controller;

import com.meanmachines.MeanStreamMachine.model.dto.request.UploadDTO;
import com.meanmachines.MeanStreamMachine.model.dto.response.DetailsDTO;
import com.meanmachines.MeanStreamMachine.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
public class WebController {
    @Value("${exceptions.UPLOAD_VALIDATION_FAIL}")
    String VALIDATION_FAIL;
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

    @RequestMapping(value = {"/", "/home"})
    public String home(Model model) {

        model.addAttribute("allMedia", DetailsDTO.mediaListToDetailDTOList(mediaService.getAllMedia()));
        return "home";
    }

    @PostMapping("/files/upload")
    public String uploadFile(Model model, @RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        UploadDTO dto = new UploadDTO();
        dto.setMedia(file);
        dto.setName(name);
        String statusMessage = "";
        boolean success = false;

        //Validate name
        if (!name.matches("^[a-zA-Z0-9 ]{1,32}$")) {
            log.error(VALIDATION_FAIL);
            statusMessage = VALIDATION_FAIL;

        } else {
            try {
                //upload file
                mediaService.processUpload(dto);
                statusMessage = "Uploaded the file successfully: " + file.getOriginalFilename();
                success = true;
            } catch (Exception e) {
                statusMessage = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            }


        }
        model.addAttribute("message", statusMessage);
        model.addAttribute("success", success);
        return "upload";
    }
}
