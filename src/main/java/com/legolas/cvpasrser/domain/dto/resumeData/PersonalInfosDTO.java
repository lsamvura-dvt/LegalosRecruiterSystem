package com.legolas.cvpasrser.domain.dto.resumeData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class PersonalInfosDTO {
    @JsonProperty("name")
    private NameDTO name;
    @JsonProperty("address")
    private AddressDTO address;
    @JsonProperty("self_summary")
    private String selfSummary;
    @JsonProperty("objective")
    private String objective;
    @JsonProperty("date_of_birth")
    private Object dateOfBirth;
    @JsonProperty("placeOfBirth")
    private Object place_of_birth;
    @JsonProperty("phones")
    private ArrayList<String> phones;
    @JsonProperty("mails")
    private ArrayList<String> mails;
    @JsonProperty("urls")
    private ArrayList<String> urls;
    @JsonProperty("fax")
    private ArrayList<String> fax;
    @JsonProperty("currentProfession")
    private String current_profession;
    @JsonProperty("gender")
    private Object gender;
    @JsonProperty("nationality")
    private String nationality;
    @JsonProperty("martial_status")
    private String martialStatus;
    @JsonProperty("current_salary")
    private String currentSalary;
}


