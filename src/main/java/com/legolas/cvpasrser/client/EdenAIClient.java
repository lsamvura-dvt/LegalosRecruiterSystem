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

import java.util.Map;

@Slf4j
@Service
public class EdenAIClient {

    private final RestTemplate restTemplate;
    private final EdenAIProperties edenAIProperties;


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
            headers.setBearerAuth(edenAIProperties.getToken());

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("providers", String.join(",", edenAIProperties.getProviders()));
            body.add("file", file);
            body.add("fallback_providers", "");

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(edenAIProperties.getUrl(), requestEntity, Map.class);
            return responseEntity.getBody();

    }
}
