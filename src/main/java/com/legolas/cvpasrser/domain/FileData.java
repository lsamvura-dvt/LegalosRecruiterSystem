package com.legolas.cvpasrser.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "file_data")
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private byte[] file;
    private byte[] extractedDataJson;
    private ResumeData resumeData;
}
