package org.example.hrsystem.controller;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.JobTypeCreateRequestDto;
import org.example.hrsystem.dto.request.JobTypeUpdateRequestDto;
import org.example.hrsystem.dto.response.JobTypeResponseDto;
import org.example.hrsystem.service.JobTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-types")
@AllArgsConstructor
public class JobTypeController {
    private final JobTypeService jobTypeService;

    @GetMapping
    public List<JobTypeResponseDto> getAllJobTypes() {
        return jobTypeService.findAllJobTypes();
    }

    @GetMapping("/{id}")
    public JobTypeResponseDto getJobTypeById(@PathVariable Long id) {
        return jobTypeService.findJobTypeById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public JobTypeResponseDto createJobType(@RequestBody JobTypeCreateRequestDto jobTypeCreateRequestDto) {
        return jobTypeService.createJobType(jobTypeCreateRequestDto);
    }

    @PutMapping
    public JobTypeResponseDto updateJobType(
            @RequestBody JobTypeUpdateRequestDto jobTypeUpdateRequestDto) {
        return jobTypeService.updateJobType(jobTypeUpdateRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteJobType(@PathVariable Long id) {
        jobTypeService.deleteJobType(id);
    }
}
