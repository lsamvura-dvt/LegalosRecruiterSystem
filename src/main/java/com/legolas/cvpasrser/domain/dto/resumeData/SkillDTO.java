package com.legolas.cvpasrser.domain.dto.resumeData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SkillDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
}
