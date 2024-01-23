package com.legolas.cvpasrser.domain.dto.resumeData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NameDTO {
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("raw_name")
    private String rawName;

    @JsonProperty("middle")
    private String middle;

    @JsonProperty("title")
    private String title;

    @JsonProperty("prefix")
    private Object prefix;

    @JsonProperty("sufix")
    private Object suffix;
}
