package com.meanmachines.MeanStreamMachine.service;

import com.meanmachines.MeanStreamMachine.exception.DatabaseFileNotFoundException;
import com.meanmachines.MeanStreamMachine.model.dbentities.Media;
import com.meanmachines.MeanStreamMachine.model.dto.request.UploadDTO;
import com.meanmachines.MeanStreamMachine.repositories.MediaRepository;
import com.meanmachines.MeanStreamMachine.util.FormatterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private StorageService storageService;

    public String writeToDb(Media media) {
        Media m = mediaRepository.save(media);
        return m.getMediaId();
    }

    public Media getMediaById(String id) {
        Media media =  mediaRepository.findByMediaId(id);
        if(media == null) throw new DatabaseFileNotFoundException("The media requested does not exist");
        return media;
    }

    public List<Media> getAllMedia() {
        return mediaRepository.selectAll();
    }

    public static String toCanonicalName(String str) {

        return str.trim().replaceAll("\\s+", "_");
    }

    public String processUpload(UploadDTO requestdto) {

        //Information
        String name = requestdto.getName();
        MultipartFile mediaFile = requestdto.getMedia();
        String extension = FilenameUtils.getExtension(mediaFile.getOriginalFilename());

        //Canonical name
        String canonicalName = toCanonicalName(requestdto.getName());
        log.info("Canonical name for " + requestdto.getName() + " is " + canonicalName);


        Media media = new Media();
        media.setTitle(name);
        media.setCanonicalName(canonicalName);
        media.setFileFormat(extension);
        media.setUploadDate(FormatterUtil.dateToString(new Date()));

        storageService.store(mediaFile);
        return writeToDb(media);
    }
}
