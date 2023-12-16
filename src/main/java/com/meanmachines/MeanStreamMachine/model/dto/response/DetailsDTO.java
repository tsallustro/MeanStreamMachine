package com.meanmachines.MeanStreamMachine.model.dto.response;

import com.meanmachines.MeanStreamMachine.model.dbentities.Media;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class DetailsDTO {
    private String id;
    private String name;
    private String type;
    private Date uploadedDate;

    public static List<DetailsDTO> mediaListToDetailDTOList(List<Media> mediaList){
        List<DetailsDTO> returnMe = new ArrayList<>();
        for(Media m : mediaList){
            returnMe.add(m.toDto());
        }
        return returnMe;
    }
}
