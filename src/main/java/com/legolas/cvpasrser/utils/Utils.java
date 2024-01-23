package com.legolas.cvpasrser.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
public class Utils {
    public static LocalDate stringToLocalDate(String date){
        if(Objects.isNull(date) || date.isBlank())
            return null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
            return LocalDate.parse(date, formatter);
        }catch (Exception e){
            log.info(String.format("Could not convert passed String to date %s, Error: %s",date,e.getMessage()));
            return null;
        }
    }
}
