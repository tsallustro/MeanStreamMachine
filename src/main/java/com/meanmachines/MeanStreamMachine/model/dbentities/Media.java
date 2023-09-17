package com.meanmachines.MeanStreamMachine.model.dbentities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Time;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "media")
public class Media {
    @Id
    @Column(name = "mediaId", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID mediaId;

    @Column(name = "title", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String title;

    @Column(name = "mediaLength", nullable = false)
    @JdbcTypeCode(SqlTypes.TIME)
    private Time mediaLength;


}