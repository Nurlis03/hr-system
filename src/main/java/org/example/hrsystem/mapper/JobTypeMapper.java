package org.example.hrsystem.mapper;

import org.example.hrsystem.dto.request.JobTypeCreateRequestDto;
import org.example.hrsystem.dto.response.JobTypeResponseDto;
import org.example.hrsystem.entity.JobType;

import java.util.List;

public interface JobTypeMapper {

    JobTypeResponseDto toJobTypeDto(JobType jobType);

    List<JobTypeResponseDto> toJobTypeDto(List<JobType> jobType);

    JobType toJobType(JobTypeCreateRequestDto createRequestDto);
}
