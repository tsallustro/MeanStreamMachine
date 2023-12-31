package com.meanmachines.MeanStreamMachine.controller;

import com.meanmachines.MeanStreamMachine.model.dto.request.RegisterRequest;
import com.meanmachines.MeanStreamMachine.model.dto.request.UploadDTO;
import com.meanmachines.MeanStreamMachine.model.dto.response.DetailsDTO;
import com.meanmachines.MeanStreamMachine.model.dto.response.RegisterResponse;
import com.meanmachines.MeanStreamMachine.service.MediaService;
import com.meanmachines.MeanStreamMachine.service.UserRegistrationManager;
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
    @Autowired
    UserRegistrationManager userRegistrationManager;

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/upload")
    String upload() {
        return "upload";
    }
    @GetMapping("/register")
    String register() {
        return "register";
    }
    @RequestMapping(value = {"/", "/home"})
    public String home(Model model) {

        model.addAttribute("allMedia", DetailsDTO.mediaListToDetailDTOList(mediaService.getAllMedia()));
        return "home";
    }
    @PostMapping("/register/new")
    public String newUser(Model model, @RequestParam("user") String user, @RequestParam("pass1")String pass1, @RequestParam("pass2")String pass2) {

        RegisterResponse response = userRegistrationManager.createNewUser(new RegisterRequest(user, pass1, pass2));

        model.addAttribute("message", response.getMessage());
        model.addAttribute("success", response.isSuccess());
        return "register";
    }
    @PostMapping("/files/upload")
    public String uploadFile(Model model, @RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        UploadDTO dto = new UploadDTO();
        dto.setMedia(file);
        dto.setName(name);
        String statusMessage;
        boolean success = false;

        //Validate name
        if (!name.matches("^[a-zA-Z0-9 ]{1,32}$")) {
            log.error(VALIDATION_FAIL);
            statusMessage = VALIDATION_FAIL;

        } else {
            try {
                //upload file
                String id = mediaService.processUpload(dto);
                statusMessage = "Uploaded the file successfully: " + file.getOriginalFilename()+" with id "+id;
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
