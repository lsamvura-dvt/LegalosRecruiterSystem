package com.legolas.cvpasrser.domain.dto.resumeData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EntryDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("location")
    private LocationDTO location;

    @JsonProperty("establishment")
    private String establishment;

    @JsonProperty("description")
    private String description;

    @JsonProperty("gpa")
    private String gpa;

    @JsonProperty("accreditation")
    private String accreditation;

    @JsonProperty("company")
    private String company;

    @JsonProperty("industry")
    private String industry;
}
