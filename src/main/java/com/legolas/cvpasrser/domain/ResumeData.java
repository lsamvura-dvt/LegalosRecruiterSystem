package com.legolas.cvpasrser.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "resume_data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;
    private LocalDateTime creationDate;
    private byte[] file;
    private byte[] extractedDataJson;
}
