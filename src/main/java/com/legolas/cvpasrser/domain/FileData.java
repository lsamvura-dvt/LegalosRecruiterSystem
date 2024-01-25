package com.legolas.cvpasrser.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "file_data")
@NoArgsConstructor
@AllArgsConstructor
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private byte[] file;
    private byte[] extractedDataJson;
    @ManyToOne
    private ResumeData resumeData;
}
