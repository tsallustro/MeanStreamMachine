package com.meanmachines.MeanStreamMachine.model.dbentities;

import com.meanmachines.MeanStreamMachine.model.dto.response.DetailsDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "media")
public class Media {
    @Id
    @Column(name = "mediaId", nullable = false)
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

    public DetailsDTO toDto(){
        return new DetailsDTO();
    }
}