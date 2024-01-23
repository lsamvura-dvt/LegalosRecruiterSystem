package com.legolas.cvpasrser.controller;

import com.legolas.cvpasrser.domain.dto.resumeData.ExtractedDataDTO;
import com.legolas.cvpasrser.service.ReadCVService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("v1/cvreader")
@RequiredArgsConstructor
public class ReadCVController {

    private final ReadCVService readCVService;

    @PostMapping("/resume")
    @Operation(summary = "Upload Resume for data extraction")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Robots found",
//                    content = { @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = RobotDto.class)) }),
//            @ApiResponse(responseCode = "404", description = "Robots not found",
//                    content = @Content) })
    public ResponseEntity<ExtractedDataDTO> UploadResume(@RequestParam("file") MultipartFile resume) {
        return ResponseEntity.ok(readCVService.readCV(resume));
    }
}
