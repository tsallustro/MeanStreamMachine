package com.meanmachines.MeanStreamMachine.model.dbentities;

import com.meanmachines.MeanStreamMachine.model.dto.response.DetailsDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "media")
public class Media {
    @Id
    @Column(name = "media_id", nullable = false, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String mediaId;

    @Column(name = "title", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String title;

    @Column(name = "canonical_name")
    private String canonicalName;

    @Column(name = "file_format", length = 10)
    private String fileFormat;

    @Column(name="upload_date")
    private String uploadDate;

    public DetailsDTO toDto(){
        DetailsDTO dto = new DetailsDTO();
        dto.setId(mediaId);
        dto.setName(title);
        dto.setType(fileFormat);
        dto.setUploadedDate(uploadDate);

        return dto;
    }
}