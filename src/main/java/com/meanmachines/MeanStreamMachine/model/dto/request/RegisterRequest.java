package com.meanmachines.MeanStreamMachine.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
    String user;
    String pass1;
    String pass2;
}
