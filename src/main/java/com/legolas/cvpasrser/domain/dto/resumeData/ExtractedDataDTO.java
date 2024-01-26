package com.legolas.cvpasrser.domain.dto.resumeData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ExtractedDataDTO {
    @JsonProperty("personal_infos")
    private PersonalInfosDTO personalInfos;
    @JsonProperty("education")
    private EducationDTO education;
    @JsonProperty("work_experience")
    private WorkExperienceDTO workExperience;
    @JsonProperty("languages")
    private ArrayList<LanguageDTO> languages;
    @JsonProperty("skills")
    private ArrayList<SkillDTO> skills;
    @JsonProperty("certifications")
    private ArrayList<CertificationDTO> certifications;
    //@JsonProperty("courses")
    //private ArrayList<Object> courses;
    //@JsonProperty("privateations")
    //private ArrayList<Object> privateations;
    //@JsonProperty("interests")
    //private ArrayList<Object> interests;
}
