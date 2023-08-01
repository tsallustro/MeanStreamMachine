package com.meanmachines.MeanStreamMachine.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class MSMController {

    //Media Details

    @GetMapping("/details/{id}")
    public ResponseEntity<HttpStatus> getDetailsById(@PathVariable UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/details/all")
    public ResponseEntity<HttpStatus> getAllMedia() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    //Stream Controls

    @GetMapping("/start/{id}")
    public ResponseEntity<HttpStatus> startStream(@PathVariable UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/stop/{streamId}")
    public ResponseEntity<HttpStatus> getAllMedia(@PathVariable UUID streamId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    //Misc
    @GetMapping("/ping")
    public ResponseEntity<HttpStatus> ping() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
