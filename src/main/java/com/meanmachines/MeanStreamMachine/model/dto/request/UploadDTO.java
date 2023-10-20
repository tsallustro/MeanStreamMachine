package com.meanmachines.MeanStreamMachine.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class UploadDTO {

    /**
     * Only allowable characters are a-zA-Z 0-9 and spaces.
     */
    private String name;


    private MultipartFile media;
}
