package com.meanmachines.MeanStreamMachine.model.dbentities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "STREAMS")
public class Stream {
    @Id
    @Column(name = "stream_id", nullable = false)
    private UUID streamId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "media_id", nullable = false)
    private Media media;

}