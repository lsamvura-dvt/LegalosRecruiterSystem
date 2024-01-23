package com.legolas.cvpasrser.domain.dto.resumeData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationDTO {
    @JsonProperty("formatted_location")
    private String formattedLocation;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("region")
    private String region;

    @JsonProperty("country")
    private String country;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("raw_input_location")
    private String rawInputLocation;

    @JsonProperty("street")
    private String street;

    @JsonProperty("street_number")
    private String streetNumber;

    @JsonProperty("appartment_number")
    private String appartmentNumber;

    @JsonProperty("city")
    private String city;
}
