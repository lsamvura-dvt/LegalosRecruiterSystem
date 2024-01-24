package com.legolas.cvpasrser.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "personal_information")
public class PersonalInformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String currentProfession;
    private String profileSummary;
    private String address;
//    private List<Skill> skills;
    private ResumeData resumeData;

}
