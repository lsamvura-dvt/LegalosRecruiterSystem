package com.legolas.cvpasrser.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.legolas.cvpasrser.domain.*;
import com.legolas.cvpasrser.domain.dto.resumeData.*;
import com.legolas.cvpasrser.repository.*;
import com.legolas.cvpasrser.service.SaveResumeDataService;
import com.legolas.cvpasrser.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SaveResumeData implements SaveResumeDataService {

    @Autowired
    private final ObjectMapper mapper;

    @Autowired
    private final ResumeDataRepository resumeDataRepository;

    @Autowired
    private final PersonalInformationRepository personalInformationRepository;

    @Autowired
    private final EducationRepository educationRepository;

    @Autowired
    private final WorkExperienceRepository workExperienceRepository;

    @Autowired
    private final SkillRepository skillRepository;

    @Autowired
    private final LanguageRepository languageRepository;


    @Override
    public ExtractedDataDTO save(Map<String, Object> responseData, MultipartFile resume) {
        try {
            JSONObject responseDataJsonObject = new JSONObject(responseData);
            //making use of eden-ai body as it combines outputs from the different providers to give a more refined response
            String extractedJsonData = responseDataJsonObject.getJSONObject("eden-ai").getJSONObject("extracted_data").toString();
            ExtractedDataDTO extractedData = mapper.readValue(extractedJsonData, ExtractedDataDTO.class);
            ResumeDataEntity resumeDataEntity = saveResumeExtractedData(extractedJsonData, resume);
            savePersonalInformation(resumeDataEntity, extractedData.getPersonalInfos());
            saveLanguage(resumeDataEntity, extractedData.getLanguages());
            saveSkills(resumeDataEntity, extractedData.getSkills());
            saveWorkExperience(resumeDataEntity, extractedData.getWorkExperience());
            saveEducation(resumeDataEntity, extractedData.getEducation());
            return extractedData;
        } catch (IOException exception) {
            log.info(String.format("Error saving data to DB: %s", exception.getMessage()));
        }
        return new ExtractedDataDTO();


    }

    private void saveEducation(ResumeDataEntity resumeDataEntity, EducationDTO education) {
        if (Objects.isNull(education) || Objects.isNull(education.getEntries()))
            return;

        List<EducationEntity> educationEntities = education.getEntries().stream().map(entry -> {
                    return EducationEntity.builder()
                            .study(entry.getAccreditation())
                            .school(entry.getEstablishment())
                            .startDate(Utils.stringToLocalDate(entry.getStartDate()))
                            .endDate(Utils.stringToLocalDate(entry.getEndDate()))
                            .resumeDataEntity(resumeDataEntity)
                            .build();
                }

        ).collect(Collectors.toList());
        educationRepository.saveAll(educationEntities);

    }

    private void saveWorkExperience(ResumeDataEntity resumeDataEntity, WorkExperienceDTO workExperience) {
        if (Objects.isNull(workExperience) || Objects.isNull(workExperience.getEntries()))
            return;

        List<WorkExperienceEntity> workExperienceEntity = workExperience.getEntries()
                .stream().map(entry -> {
                    return WorkExperienceEntity.builder()
                            .company(entry.getCompany())
                            .location(entry.getLocation() == null ? null : entry.getLocation().getFormattedLocation())
                            .title(entry.getTitle())
                            .startDate(Utils.stringToLocalDate(entry.getStartDate()))
                            .endDate(Utils.stringToLocalDate(entry.getEndDate()))
                            .build();
                }).collect(Collectors.toList());

        workExperienceRepository.saveAll(workExperienceEntity);
    }

    private void saveSkills(ResumeDataEntity resumeDataEntity, ArrayList<SkillDTO> skills) {
        if (Objects.isNull(skills) || skills.size() == 0)
            return;

        List<SkillEntity> skillEntities = skills.stream().map(
                skill -> {
                    return SkillEntity.builder()
                            .resumeDataEntity(resumeDataEntity)
                            .skill(skill.getName())
                            .type(skill.getType())
                            .build();
                }
        ).collect(Collectors.toList());
        skillRepository.saveAll(skillEntities);
    }

    private void saveLanguage(ResumeDataEntity resumeDataEntity, ArrayList<LanguageDTO> languages) {
        if (Objects.isNull(languages) || languages.size() == 0)
            return;

        List<LanguageEntity> languageEntities = languages.stream()
                .map(language -> {
                    return LanguageEntity.builder()
                            .language(language.getName())
                            .resumeDataEntity(resumeDataEntity)
                            .build();
                }).collect(Collectors.toList());
        languageRepository.saveAll(languageEntities);
    }

    private void savePersonalInformation(ResumeDataEntity resumeDataEntity, PersonalInfosDTO personalInfos) {
        PersonalInformationEntity personalInformationEntity = PersonalInformationEntity.builder()
                .resumeDataEntity(resumeDataEntity)
                .phone(personalInfos.getPhones() == null ? null : personalInfos.getPhones().get(0))
                .email(personalInfos.getMails() == null ? null : personalInfos.getMails().get(0))
                .address(personalInfos.getAddress() == null ? null : personalInfos.getAddress().getFormattedLocation())
                .firstName(personalInfos.getName() == null ? null : personalInfos.getName().getFirstName())
                .lastName(personalInfos.getName() == null ? null : personalInfos.getName().getLastName())
                .profileSummary(personalInfos.getSelfSummary())
                .build();
        personalInformationRepository.save(personalInformationEntity);
    }

    private ResumeDataEntity saveResumeExtractedData(String edenAIJson, MultipartFile resume) throws IOException {
        ResumeDataEntity resumeDataEntity = ResumeDataEntity.builder()
                .creationDate(LocalDateTime.now())
//                .extractedData(edenAIJson)
                .fileName(resume.getOriginalFilename())
                .file(resume.getBytes())
                .build();
        return resumeDataRepository.save(resumeDataEntity);
    }

}
