package org.example.hrsystem.mapper.impl;

import org.example.hrsystem.dto.request.JobTypeCreateRequestDto;
import org.example.hrsystem.dto.response.JobTypeResponseDto;
import org.example.hrsystem.entity.JobType;
import org.example.hrsystem.mapper.JobTypeMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobTypeMapperImpl implements JobTypeMapper {

    @Override
    public JobTypeResponseDto toJobTypeDto(JobType jobType) {
        return JobTypeResponseDto
                .builder()
                .id(jobType.getId())
                .jobTypeTitleKg(jobType.getJobTypeTitleKg())
                .jobTypeTitleRu(jobType.getJobTypeTitleRu())
                .build();
    }

    @Override
    public List<JobTypeResponseDto> toJobTypeDto(List<JobType> jobTypes) {
        return jobTypes
                .stream()
                .map(this::toJobTypeDto)
                .toList();
    }

    @Override
    public JobType toJobType(JobTypeCreateRequestDto createRequestDto) {
        return JobType
                .builder()
                .jobTypeTitleKg(createRequestDto.getJobTypeTitleKg())
                .jobTypeTitleRu(createRequestDto.getJobTypeTitleRu())
                .build();
    }
}
