package com.meanmachines.MeanStreamMachine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MediaDetails {
    String name;
    UUID id;
}
