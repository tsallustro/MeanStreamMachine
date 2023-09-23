package com.meanmachines.MeanStreamMachine.service;

import com.meanmachines.MeanStreamMachine.model.dbentities.Media;
import com.meanmachines.MeanStreamMachine.model.dto.UploadDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class UploadService {
    @Autowired
    private StorageService storageService;

    public static String toCanonicalName(String str){

        return str.trim().replaceAll("\\s+","_");
    }

    public void processUpload(UploadDTO requestdto) {

        //Information
        String name = requestdto.getName();
        MultipartFile mediaFile = requestdto.getMedia();
        String extension = FilenameUtils.getExtension(mediaFile.getOriginalFilename());

        //Canonical name
        String canonicalName = toCanonicalName(requestdto.getName());
        log.info("Canonical name for "+requestdto.getName()+" is "+canonicalName);


        Media media = new Media();
        media.setTitle(name);
        media.setCanonicalName(canonicalName);
        media.setFileFormat(extension);

        storageService.storeAndWriteToDb(mediaFile, media);
    }
}
