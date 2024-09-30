package org.example.hrsystem.service.impl;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.JobTypeCreateRequestDto;
import org.example.hrsystem.dto.request.JobTypeUpdateRequestDto;
import org.example.hrsystem.dto.response.JobTypeResponseDto;
import org.example.hrsystem.entity.JobType;
import org.example.hrsystem.exception.NotFoundException;
import org.example.hrsystem.mapper.JobTypeMapper;
import org.example.hrsystem.repository.JobTypeRepository;
import org.example.hrsystem.service.JobTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobTypeServiceImpl implements JobTypeService {

    private final JobTypeRepository jobTypeRepository;
    private final JobTypeMapper jobTypeMapper;

    @Override
    public List<JobTypeResponseDto> findAllJobTypes() {
        return jobTypeMapper.toJobTypeDto (
                jobTypeRepository.findAll()
        );
    }

    @Override
    public JobTypeResponseDto findJobTypeById(Long id) {
        JobType jobType = jobTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("JobType not found"));
        return jobTypeMapper.toJobTypeDto(jobType);
    }

    @Override
    public JobTypeResponseDto createJobType(JobTypeCreateRequestDto createRequestDto) {
        JobType jobType = jobTypeMapper.toJobType(createRequestDto);
        jobTypeRepository.save(jobType);
        return jobTypeMapper.toJobTypeDto(jobType);
    }

    @Override
    public JobTypeResponseDto updateJobType(JobTypeUpdateRequestDto updateRequestDto) {
        JobType jobType = jobTypeRepository.findById(updateRequestDto.getId())
                .orElseThrow(() -> new NotFoundException("JobType not found"));

        jobType.setJobTypeTitleKg(updateRequestDto.getJobTypeTitleKg());
        jobType.setJobTypeTitleRu(updateRequestDto.getJobTypeTitleRu());

        jobTypeRepository.save(jobType);
        return jobTypeMapper.toJobTypeDto(jobType);
    }

    @Override
    public void deleteJobType(Long id) {
        jobTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("JobType not found"));
        jobTypeRepository.deleteById(id);
    }
}
