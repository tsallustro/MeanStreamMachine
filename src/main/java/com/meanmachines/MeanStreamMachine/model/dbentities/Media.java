package com.meanmachines.MeanStreamMachine.model.dbentities;

import com.meanmachines.MeanStreamMachine.model.dto.response.DetailsDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "media")
public class Media {
    @Id
    @Column(name = "media_id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID mediaId;

    @Column(name = "title", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String title;

    @Column(name = "canonical_name")
    private String canonicalName;

    @Column(name = "file_format", length = 10)
    private String fileFormat;

    @Column(name="upload_date")
    private Date uploadDate;

    public DetailsDTO toDto(){
        DetailsDTO dto = new DetailsDTO();
        dto.setName(title);
        dto.setType(fileFormat);
        dto.setUploadedDate(uploadDate);

        return dto;
    }
}