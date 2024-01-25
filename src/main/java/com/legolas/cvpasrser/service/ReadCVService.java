package com.legolas.cvpasrser.service;

import com.legolas.cvpasrser.domain.dto.resumeData.ExtractedDataDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ReadCVService {

    /**
     * This method receives uploaded resume & calls the edenAI endpoint to extract data from the Resume
     * @param resume uploaded resume file
     * @return ExtractedDataDTO POJO class populated with data exctracted from the resume
     */
    ExtractedDataDTO readCV(MultipartFile resume);
}
