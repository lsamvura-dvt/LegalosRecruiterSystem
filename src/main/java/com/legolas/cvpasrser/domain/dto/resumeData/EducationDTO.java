package com.legolas.cvpasrser.domain.dto.resumeData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class EducationDTO {
    @JsonProperty("total_years_education")
    private Integer totalYearsEducation;
    @JsonProperty("entries")
    private ArrayList<EntryDTO> entries;
}
