package com.meanmachines.MeanStreamMachine.model.dbentities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="test")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TestEntity {

    @Id
    private long id;
    private String note;
}
