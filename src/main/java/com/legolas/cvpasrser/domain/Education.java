package com.legolas.cvpasrser.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "education")
@Data
@Builder
public class Education implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String school;
    private String study;
    private LocalDate startDate;
    private LocalDate endDate;
    private ResumeData resumeData;
}
