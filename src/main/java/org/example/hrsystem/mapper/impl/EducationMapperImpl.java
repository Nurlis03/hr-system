package org.example.hrsystem.mapper.impl;

import org.example.hrsystem.dto.response.EducationResponseDto;
import org.example.hrsystem.entity.Education;
import org.example.hrsystem.mapper.EducationMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EducationMapperImpl implements EducationMapper {
    @Override
    public EducationResponseDto toEducationDto(Education education) {
        return EducationResponseDto
                .builder()
                .educationTitleKg(education.getEducationTitleKg())
                .educationTitleRu(education.getEducationTitleRu())
                .gpa(education.getGpa())
                .degreeKg(education.getDegreeKg())
                .degreeRu(education.getDegreeRu())
                .eduFrom(education.getEduFrom())
                .eduTo(education.getEduTo())
                .build();
    }

    @Override
    public List<EducationResponseDto> toEducationDto(List<Education> educations) {
        return educations
                .stream()
                .map(this::toEducationDto)
                .toList();
    }
}
