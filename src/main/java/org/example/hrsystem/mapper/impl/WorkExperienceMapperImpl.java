package org.example.hrsystem.mapper.impl;

import org.example.hrsystem.dto.response.WorkExperienceResponseDto;
import org.example.hrsystem.entity.WorkExperience;
import org.example.hrsystem.mapper.WorkExperienceMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkExperienceMapperImpl implements WorkExperienceMapper {
    @Override
    public WorkExperienceResponseDto toWorkExperienceResponseDto(WorkExperience workExperience) {
        return WorkExperienceResponseDto
                .builder()
                .expFrom(workExperience.getExpFrom())
                .expTo(workExperience.getExpTo())
                .jobTypeTitleKg(workExperience.getJobType().getJobTypeTitleKg())
                .jobTypeTitleRu(workExperience.getJobType().getJobTypeTitleRu())
                .positionTitleRu(workExperience.getPosition().getPositionTitleRu())
                .positionTitleKg(workExperience.getPosition().getPositionTitleKg())
                .companyTitleRu(workExperience.getCompany().getCompanyTitleRu())
                .companyTitleKg(workExperience.getCompany().getCompanyTitleKg())
                .expDescription(workExperience.getExpDescription())
                .build();
    }

    @Override
    public List<WorkExperienceResponseDto> toWorkExperienceResponseDto(List<WorkExperience> workExperiences) {
        return workExperiences
                .stream()
                .map(this::toWorkExperienceResponseDto)
                .toList();
    }
}
