package com.meanmachines.MeanStreamMachine.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    String message;
    String causeName;
    HttpStatus status;
}
