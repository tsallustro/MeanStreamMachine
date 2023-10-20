package com.meanmachines.MeanStreamMachine.controller;

import com.meanmachines.MeanStreamMachine.model.dbentities.Media;
import com.meanmachines.MeanStreamMachine.model.dto.request.UploadDTO;
import com.meanmachines.MeanStreamMachine.model.dto.response.DetailsDTO;
import com.meanmachines.MeanStreamMachine.model.dto.response.UploadResponse;
import com.meanmachines.MeanStreamMachine.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        log.info("Recieved details request for " + mediaId);
        return mediaService.getMediaById(mediaId).toDto();
    }

    @GetMapping("/api/details/all")
    public List<DetailsDTO> getAllMedia() {


        log.info("Recieved all media request");

        return DetailsDTO.mediaListToDetailDTOList(mediaService.getAllMedia());
    }

    //Stream Controls

    @GetMapping("/api/start/{mediaId}")
    public ResponseEntity<HttpStatus> startStream(@PathVariable UUID mediaId) {

        log.info("Recieved start request for " + mediaId);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);


    }

    @GetMapping("/api/stop/{streamId}")
    public ResponseEntity<HttpStatus> getAllMedia(@PathVariable UUID streamId) {
        log.info("Recieved stop request for " + streamId);

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/api/upload")
    public UploadResponse uploadMedia(@ModelAttribute UploadDTO requestdto) {
        log.info("Recieved upload request. Name is " + requestdto.getName() + "; Media size is " + requestdto.getMedia().getSize() + " bytes");
        UploadResponse response = new UploadResponse();

        response.setMediaId(mediaService.processUpload(requestdto));
        return response;

    }

    //Misc
    @GetMapping("/api/ping")
    public ResponseEntity<HttpStatus> ping() {
        log.info("Recieved ping request");
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
