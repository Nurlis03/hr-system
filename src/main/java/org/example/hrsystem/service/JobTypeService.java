package org.example.hrsystem.service;

import org.example.hrsystem.dto.request.JobTypeCreateRequestDto;
import org.example.hrsystem.dto.request.JobTypeUpdateRequestDto;
import org.example.hrsystem.dto.response.JobTypeResponseDto;

import java.util.List;

public interface JobTypeService {
    List<JobTypeResponseDto> findAllJobTypes();

    JobTypeResponseDto findJobTypeById(Long id);

    JobTypeResponseDto createJobType(JobTypeCreateRequestDto createRequestDto);

    JobTypeResponseDto updateJobType(JobTypeUpdateRequestDto updateRequestDto);

    void deleteJobType(Long id);
}
