package com.meanmachines.MeanStreamMachine.service;

import com.meanmachines.MeanStreamMachine.exception.StorageException;
import com.meanmachines.MeanStreamMachine.exception.StorageFileNotFoundException;
import com.meanmachines.MeanStreamMachine.model.dbentities.Media;
import com.meanmachines.MeanStreamMachine.repositories.MediaRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

@Service

public class StorageService {

    @Getter
    @Value("${storage.media-dir}")
    private Path mediaDirectory;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    public StorageService() {

    }


    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.mediaDirectory.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.mediaDirectory.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }



    public void init() {
        try {
            Files.createDirectories(mediaDirectory);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    public void storeAndWriteToDb(MultipartFile file, Media media){
        store(file);
        mediaRepository.save(media);
    }




}