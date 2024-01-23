package com.legolas.cvpasrser.controller.advice;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class ErrorDecoder {

        private static final ObjectMapper MAPPER = new ObjectMapper();

        public static ErrorResponse decodeExceptionResponse(HttpStatusCodeException httpStatusCodeException, WebRequest
        webRequest) {
            ErrorResponse errorResponse;
            try {
                String exception = httpStatusCodeException.getResponseBodyAsString();
                errorResponse = MAPPER.readValue(exception, ErrorResponse.class);
            } catch (IOException ioException) {
                log.error("Unable to reade message from server Exception. Reason : {}", ioException.getMessage());
                errorResponse = fallbackErrorResponse(httpStatusCodeException, webRequest);
            }
            return errorResponse;
        }

        private static ErrorResponse fallbackErrorResponse(HttpStatusCodeException httpStatusCodeException, WebRequest request) {
            String exceptionMessage = httpStatusCodeException.getResponseBodyAsString();
            if (StringUtils.isEmpty(exceptionMessage) || StringUtils.isBlank(exceptionMessage)) {
                exceptionMessage = String.format("Exception occurred for request %s", request.getDescription(false));
            }
            ReasonCode reasonCode = new ReasonCode(HttpStatus.INTERNAL_SERVER_ERROR.name(),exceptionMessage);
            return ErrorResponse.builder()
                    .reasonCodes(List.of(reasonCode))
                    .build();
        }


}
