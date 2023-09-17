package com.meanmachines.MeanStreamMachine.controller;

import com.meanmachines.MeanStreamMachine.model.dbentities.Media;
import com.meanmachines.MeanStreamMachine.model.dbentities.Test;
import com.meanmachines.MeanStreamMachine.repositories.MediaRepository;
import com.meanmachines.MeanStreamMachine.repositories.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
public class MSMController {
    private final TestRepository testRepository;
    private final MediaRepository mediaRepository;

    @Autowired
    public MSMController(TestRepository testRepository, MediaRepository mediaRepository) {
        this.testRepository = testRepository;
        this.mediaRepository = mediaRepository;
    }

    //Media Details

    @GetMapping("/details/{mediaId}")
    public Media getDetailsById(@PathVariable UUID mediaId) {
        log.info("Recieved details request for " + mediaId);

        return mediaRepository.findByMediaId(mediaId);
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

    //Misc
    @GetMapping("/ping")
    public ResponseEntity<HttpStatus> ping() {
        log.info("Recieved ping request");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/dbtest/{id}")
    public Test dbtest(@PathVariable Integer id) {

        log.info("Recieved dbtest request " + id);
        Optional<Test> test = testRepository.findById(id);
        return test.orElse(null);

    }

}
