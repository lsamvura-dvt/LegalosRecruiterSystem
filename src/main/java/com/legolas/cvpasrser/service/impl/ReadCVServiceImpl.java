package com.legolas.cvpasrser.service.impl;

import com.legolas.cvpasrser.client.EdenAIClient;
import com.legolas.cvpasrser.domain.dto.resumeData.ExtractedDataDTO;
import com.legolas.cvpasrser.service.ReadCVService;
import com.legolas.cvpasrser.service.SaveResumeDataService;
import com.legolas.cvpasrser.utils.EdenAIProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ReadCVServiceImpl implements ReadCVService {

    private final RestTemplate restTemplate;
    private final EdenAIProperties edenAIProperties;
    private final SaveResumeDataService saveResumeDataService;

    private final EdenAIClient edenAIClient;


    @Autowired
    public ReadCVServiceImpl(RestTemplate restTemplate, EdenAIProperties edenAIProperties, SaveResumeDataService saveResumeDataService, EdenAIClient edenAIClient) {
        this.restTemplate = restTemplate;
        this.edenAIProperties = edenAIProperties;
        this.saveResumeDataService = saveResumeDataService;
        this.edenAIClient = edenAIClient;
    }

    @Override
    public ExtractedDataDTO readCV(MultipartFile resume) {
        Map savedResponse = new HashMap<>();
        try {
            ByteArrayResource file = new ByteArrayResource(resume.getBytes()) {
                @Override
                public String getFilename() {
                    return resume.getOriginalFilename();
                }
            };
            return saveResumeDataService.save(edenAIClient.getResumeMetadata(file), resume);

        } catch (IOException e) {
            log.info(String.format("Error reading file: %s", e.getMessage()));
            e.printStackTrace();
        }
        return new ExtractedDataDTO();
    }
}
