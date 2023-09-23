package com.meanmachines.MeanStreamMachine.controller;

import com.meanmachines.MeanStreamMachine.model.dto.UploadDTO;
import com.meanmachines.MeanStreamMachine.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
public class MSMController {


    @Autowired
    private UploadService uploadService;

    @Autowired
    public MSMController() {

    }

    //Media Details

    @GetMapping("/details/{mediaId}")
    public ResponseEntity<HttpStatus> getDetailsById(@PathVariable UUID mediaId) {
        log.info("Recieved details request for " + mediaId);

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/details/all")
    public ResponseEntity<HttpStatus> getAllMedia() {


        log.info("Recieved all media request");

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    //Stream Controls

    @GetMapping("/start/{mediaId}")
    public ResponseEntity<HttpStatus> startStream(@PathVariable UUID mediaId) {

        log.info("Recieved start request for " + mediaId);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);


    }

    @GetMapping("/stop/{streamId}")
    public ResponseEntity<HttpStatus> getAllMedia(@PathVariable UUID streamId) {
        log.info("Recieved stop request for " + streamId);

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/upload")
    public ResponseEntity<HttpStatus> uploadMedia(@ModelAttribute UploadDTO requestdto){
        log.info("Recieved upload request. Name is "+requestdto.getName()+"; Media size is "+requestdto.getMedia().getSize() + " bytes");
        uploadService.processUpload(requestdto);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);


    }

    //Misc
    @GetMapping("/ping")
    public ResponseEntity<HttpStatus> ping() {
        log.info("Recieved ping request");
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
