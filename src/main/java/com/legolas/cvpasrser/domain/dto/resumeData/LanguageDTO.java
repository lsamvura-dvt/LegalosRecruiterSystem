package com.legolas.cvpasrser.domain.dto.resumeData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LanguageDTO {

    @JsonProperty("name")
    private String name;

//    @JsonProperty("code")
//    private Object code;
}
