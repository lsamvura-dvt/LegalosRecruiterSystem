package com.legolas.cvpasrser.domain.dto.resumeData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RootDTO {
    @JsonProperty("status")
    private String status;
    @JsonProperty("extracted_data")
    private ExtractedDataDTO extractedData;
    @JsonProperty("cost")
    private double cost;
}
