package com.legolas.cvpasrser.client;

import com.legolas.cvpasrser.configs.EdenAIProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
public class EdenAIClient {

    private final RestTemplate restTemplate;
    private final EdenAIProperties edenAIProperties;

    public String getResumeMetadata(ByteArrayResource file) {
        log.info("Eden ai providers:{}", edenAIProperties.getProviders());
        log.info("Eden ai url:{}", edenAIProperties.getUrl());
        log.info("Eden ai token:{}", edenAIProperties.getToken());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setBearerAuth(edenAIProperties.getToken());

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("providers", String.join(",", edenAIProperties.getProviders()));
        body.add("file", file);
        body.add("fallback_providers", "");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(edenAIProperties.getUrl(), requestEntity, String.class);

        return responseEntity.getBody();

    }
}
