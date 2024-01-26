package com.legolas.cvpasrser.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "work_experience")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String company;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;

    @ManyToOne
    private ResumeData resumeData;

}
