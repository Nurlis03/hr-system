package org.example.hrsystem.mapper;

import org.example.hrsystem.dto.response.WorkExperienceResponseDto;
import org.example.hrsystem.entity.WorkExperience;

import java.util.List;

public interface WorkExperienceMapper {
    WorkExperienceResponseDto toWorkExperienceResponseDto(WorkExperience workExperience);
    List<WorkExperienceResponseDto> toWorkExperienceResponseDto(List<WorkExperience> workExperiences);
}
