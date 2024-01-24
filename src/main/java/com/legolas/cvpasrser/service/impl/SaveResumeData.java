package com.legolas.cvpasrser.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.legolas.cvpasrser.domain.*;
import com.legolas.cvpasrser.domain.dto.resumeData.*;
import com.legolas.cvpasrser.repository.*;
import com.legolas.cvpasrser.service.SaveResumeDataService;
import com.legolas.cvpasrser.utils.Utils;
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
public class SaveResumeData implements SaveResumeDataService {

    private final ObjectMapper mapper;
    private final ResumeDataRepository resumeDataRepository;
    private final PersonalInformationRepository personalInformationRepository;
    private final EducationRepository educationRepository;
    private final WorkExperienceRepository workExperienceRepository;
    private final SkillRepository skillRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public SaveResumeData(ObjectMapper mapper, ResumeDataRepository resumeDataRepository, PersonalInformationRepository personalInformationRepository,
                          EducationRepository educationRepository, WorkExperienceRepository workExperienceRepository, SkillRepository skillRepository,
                          LanguageRepository languageRepository) {
        this.mapper = mapper;
        this.resumeDataRepository = resumeDataRepository;
        this.personalInformationRepository = personalInformationRepository;
        this.educationRepository = educationRepository;
        this.workExperienceRepository = workExperienceRepository;
        this.skillRepository = skillRepository;
        this.languageRepository = languageRepository;
    }


    @Override
    public ExtractedDataDTO save(Map<String, Object> responseData, MultipartFile resume) {
        try {
            JSONObject responseDataJsonObject = new JSONObject(responseData);
            //making use of eden-ai body as it combines outputs from the different providers to give a more refined response
            String extractedJsonData = responseDataJsonObject.getJSONObject("eden-ai").getJSONObject("extracted_data").toString();
            ExtractedDataDTO extractedData = mapper.readValue(extractedJsonData, ExtractedDataDTO.class);
            ResumeData resumeData = saveResumeExtractedData(extractedJsonData, resume);
            savePersonalInformation(resumeData, extractedData.getPersonalInfos());
            saveLanguage(resumeData, extractedData.getLanguages());
            saveSkills(resumeData, extractedData.getSkills());
            saveWorkExperience(resumeData, extractedData.getWorkExperience());
            saveEducation(resumeData, extractedData.getEducation());
            return extractedData;
        } catch (IOException exception) {
            log.info(String.format("Error saving data to DB: %s", exception.getMessage()));
        }
        return new ExtractedDataDTO();


    }


    private void saveEducation(ResumeData resumeData, EducationDTO education) {
        if (Objects.isNull(education) || Objects.isNull(education.getEntries()))
            return;

        List<Education> educationEntities = education.getEntries()
                .stream()
                .map(entry -> Education.builder()
                            .study(entry.getAccreditation())
                            .school(entry.getEstablishment())
                            .startDate(Utils.stringToLocalDate(entry.getStartDate()))
                            .endDate(Utils.stringToLocalDate(entry.getEndDate()))
                            .resumeData(resumeData)
                            .build()
                ).collect(Collectors.toList());
        educationRepository.saveAll(educationEntities);

    }

    private void saveWorkExperience(ResumeData resumeData, WorkExperienceDTO workExperience) {
        if (Objects.isNull(workExperience) || Objects.isNull(workExperience.getEntries()))
            return;

        List<WorkExperience> workExperienceEntity = workExperience.getEntries()
                .stream().map(entry -> WorkExperience.builder()
                        .company(entry.getCompany())
                        .location(entry.getLocation() == null ? null : entry.getLocation().getFormattedLocation())
                        .title(entry.getTitle())
                        .resumeData(resumeData)
                        .startDate(Utils.stringToLocalDate(entry.getStartDate()))
                        .endDate(Utils.stringToLocalDate(entry.getEndDate()))
                        .build()).collect(Collectors.toList());
        workExperienceRepository.saveAll(workExperienceEntity);
    }

    private void saveSkills(ResumeData resumeData, ArrayList<SkillDTO> skills) {
        if (Objects.isNull(skills) || skills.size() == 0)
            return;

        List<Skill> skillEntities = skills.stream().map(
                skill -> Skill.builder()
                        .resumeData(resumeData)
                        .skill(skill.getName())
                        .type(skill.getType())
                        .build()

        ).collect(Collectors.toList());
        skillRepository.saveAll(skillEntities);
    }

    private void saveLanguage(ResumeData resumeData, ArrayList<LanguageDTO> languages) {
        if (Objects.isNull(languages) || languages.size() == 0)
            return;

        List<Language> languageEntities = languages.stream()
                .map(language -> Language.builder()
                        .language(language.getName())
                        .resumeData(resumeData)
                        .build()
                ).collect(Collectors.toList());
        languageRepository.saveAll(languageEntities);
    }

    private void savePersonalInformation(ResumeData resumeData, PersonalInfosDTO personalInfos) {
        PersonalInformation personalInformation = PersonalInformation.builder()
                .resumeData(resumeData)
                .phone(personalInfos.getPhones() == null ? null : personalInfos.getPhones().get(0))
                .email(personalInfos.getMails() == null ? null : personalInfos.getMails().get(0))
                .address(personalInfos.getAddress() == null ? null : personalInfos.getAddress().getFormattedLocation())
                .firstName(personalInfos.getName() == null ? null : personalInfos.getName().getFirstName())
                .lastName(personalInfos.getName() == null ? null : personalInfos.getName().getLastName())
                .profileSummary(personalInfos.getSelfSummary())
                .build();
        personalInformationRepository.save(personalInformation);
    }

    private ResumeData saveResumeExtractedData(String edenAIJson, MultipartFile resume) throws IOException {
        ResumeData resumeData = ResumeData.builder()
                .creationDate(LocalDateTime.now())
                .fileName(resume.getOriginalFilename())
                .extractedDataJson(edenAIJson.getBytes())
                .file(resume.getBytes())
                .build();
        return resumeDataRepository.save(resumeData);
    }

}
