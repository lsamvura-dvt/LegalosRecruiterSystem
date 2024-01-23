package com.legolas.cvpasrser.controller;

import com.legolas.cvpasrser.domain.dto.resumeData.ExtractedDataDTO;
import com.legolas.cvpasrser.service.ReadCVService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("v1/cvreader")
@RequiredArgsConstructor
public class ReadCVController {

    private final ReadCVService readCVService;

    @RequestMapping(
            path = "/upload",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload Resume for data extraction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metadata exctracted from resume", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExtractedDataDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Error retrieving data", content = @Content)
    })
    public ResponseEntity<ExtractedDataDTO> UploadResume(@RequestParam("file") MultipartFile resume) {
        return ResponseEntity.ok(readCVService.readCV(resume));
    }
}
