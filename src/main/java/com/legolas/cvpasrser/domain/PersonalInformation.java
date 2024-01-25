package com.legolas.cvpasrser.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "personal_information")
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToOne
    private ResumeData resumeData;

}
