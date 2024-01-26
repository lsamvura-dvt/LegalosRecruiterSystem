package com.legolas.cvpasrser.domain.dto.resumeData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class WorkExperienceDTO {

    @JsonProperty("total_years_experience")
    private String totalYearsExperience;

    @JsonProperty("entries")
    private ArrayList<EntryDTO> entries;
}
