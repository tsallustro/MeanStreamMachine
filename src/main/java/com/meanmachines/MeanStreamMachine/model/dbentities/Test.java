package com.meanmachines.MeanStreamMachine.model.dbentities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "test")
public class Test {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "note", nullable = false, length = 40)
    private String note;

}