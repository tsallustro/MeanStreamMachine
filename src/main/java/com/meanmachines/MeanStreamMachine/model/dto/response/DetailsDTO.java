package com.meanmachines.MeanStreamMachine.model.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class DetailsDTO {
    private String name;
    private String type;
    private Date uploadedDate;
}
