package com.legolas.cvpasrser.domain.dto.resumeData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CertificationDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private Object type;
}
