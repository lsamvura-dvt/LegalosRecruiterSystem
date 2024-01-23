package com.legolas.cvpasrser.controller.advice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
class ReasonCode implements Serializable {

    private int code;
    private String message;
}
