package com.legolas.cvpasrser.service.impl;

import com.legolas.cvpasrser.client.EdenAIClient;
import com.legolas.cvpasrser.domain.dto.resumeData.ExtractedDataDTO;
import com.legolas.cvpasrser.service.ReadCVService;
import com.legolas.cvpasrser.service.SaveResumeDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ReadCVServiceImpl implements ReadCVService {

    private final SaveResumeDataService saveResumeDataService;
    private final EdenAIClient edenAIClient;


    @Autowired
    public ReadCVServiceImpl(SaveResumeDataService saveResumeDataService,
                             EdenAIClient edenAIClient) {
        this.saveResumeDataService = saveResumeDataService;
        this.edenAIClient = edenAIClient;
    }

    @Override
    public ExtractedDataDTO readCV(MultipartFile resume) {
        try {
            ByteArrayResource file = new ByteArrayResource(resume.getBytes()) {
                @Override
                public String getFilename() {
                    return resume.getOriginalFilename();
                }
            };
            String responseJson = edenAIClient.getResumeMetadata(file);
            return saveResumeDataService.save(responseJson, resume);

        } catch (IOException e) {
            log.info(String.format("Error reading file: %s", e.getMessage()));
            e.printStackTrace();
        }
        return new ExtractedDataDTO();
    }
}
