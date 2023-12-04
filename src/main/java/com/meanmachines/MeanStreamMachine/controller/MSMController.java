package com.meanmachines.MeanStreamMachine.controller;

import com.meanmachines.MeanStreamMachine.model.dto.request.UploadDTO;
import com.meanmachines.MeanStreamMachine.model.dto.response.DetailsDTO;
import com.meanmachines.MeanStreamMachine.model.dto.response.UploadResponse;
import com.meanmachines.MeanStreamMachine.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class MSMController {


    @Autowired
    private MediaService mediaService;

    @Autowired
    public MSMController() {

    }

    //Media Details

    @GetMapping("/api/details/{mediaId}")
    public DetailsDTO getDetailsById(@PathVariable UUID mediaId) {
        log.info("Received details request for " + mediaId);
        return mediaService.getMediaById(mediaId).toDto();
    }

    @GetMapping("/api/details/all")
    public List<DetailsDTO> getAllMedia() {
        log.info("Received all media request");
        return DetailsDTO.mediaListToDetailDTOList(mediaService.getAllMedia());
    }

    //Stream Controls
    @GetMapping("/api/start/{mediaId}")
    public RedirectView startStream(@PathVariable UUID mediaId) {

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://www.google.com");
        return redirectView;


    }

    @GetMapping("/api/stop/{streamId}")
    public ResponseEntity<HttpStatus> stopStream(@PathVariable UUID streamId) {
        log.info("Received stop request for " + streamId);

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/api/upload")
    public UploadResponse uploadMedia(@ModelAttribute UploadDTO uploadDTO) {
        log.info("Received upload request. Name is " + uploadDTO.getName() + "; Media size is " + uploadDTO.getMedia().getSize() + " bytes");
        UploadResponse response = new UploadResponse();

        response.setMediaId(mediaService.processUpload(uploadDTO));
        return response;

    }

    //Misc
    @GetMapping("/api/ping")
    public ResponseEntity<HttpStatus> ping() {

        log.info("Received ping request");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
