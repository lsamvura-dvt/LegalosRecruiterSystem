package com.legolas.cvpasrser.service;

import com.legolas.cvpasrser.domain.dto.resumeData.ExtractedDataDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface SaveResumeDataService {

    /**
     * This method receives Map response from edenAi & name of the file uploaded, creates POJO classes from the passed data
     * and saves to database
     * @param responseJson response Json String from edenAI endpoint
     * @param resume MultipartFile of resume uploaded
     * @return ExtractedDataDTO Pojo class of data extracted from the responseData Map
     */
    public ExtractedDataDTO save(String responseJson,  MultipartFile resume);
}
