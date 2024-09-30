package org.example.hrsystem.mapper;

import org.example.hrsystem.dto.response.EducationResponseDto;
import org.example.hrsystem.entity.Education;

import java.util.List;

public interface EducationMapper {
    EducationResponseDto toEducationDto(Education education);
    List<EducationResponseDto> toEducationDto(List<Education> educations);
}
