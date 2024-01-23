package com.legolas.cvpasrser.client;

import com.legolas.cvpasrser.utils.EdenAIProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
public class EdenAIClient {

    private final RestTemplate restTemplate;
    private final EdenAIProperties edenAIProperties;

    private final String EDENAITOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiZDQ2NzQ3ZWQtMTk3NC00Y2YzLThkNDYtZDFmNzg2ODMxNmM5IiwidHlwZSI6ImFwaV90b2tlbiJ9.AiO_28Lv-MK08lyAP8Mi-ilwPjufbSaJV9ghU5HGvMw";

    @Autowired
    public EdenAIClient(RestTemplate restTemplate, EdenAIProperties edenAIProperties) {
        this.restTemplate = restTemplate;
        this.edenAIProperties = edenAIProperties;
    }

    public Map<String, Object>  getResumeMetadata(ByteArrayResource file){

        log.info("Eden ai providers:{}", edenAIProperties.getProviders());
        log.info("Eden ai url:{}", edenAIProperties.getUrl());
        log.info("Eden ai token:{}", edenAIProperties.getToken());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//            headers.setBearerAuth(edenAIProperties.getToken());
            headers.setBearerAuth(EDENAITOKEN);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("providers", String.join(",", edenAIProperties.getProviders()));
            body.add("file", file);
            body.add("fallback_providers", "");

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(edenAIProperties.getUrl(), requestEntity, Map.class);
            return responseEntity.getBody();

    }
}
