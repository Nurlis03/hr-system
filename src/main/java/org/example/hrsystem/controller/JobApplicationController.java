package org.example.hrsystem.controller;

import io.minio.errors.*;
import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.JobApplicationRequestDto;
import org.example.hrsystem.dto.request.JobApplicationUpdateRequestDto;
import org.example.hrsystem.dto.response.JobApplicationResponseDto;
import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.service.JobApplicationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/job-applications")
@AllArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @PostMapping("/applicant")
    public JobApplicationResponseDto respondToVacancy(
            @AuthenticationPrincipal Applicant applicant,
            @ModelAttribute JobApplicationRequestDto jobApplicationRequestDto)
            throws ServerException,
            InsufficientDataException,
            ErrorResponseException,
            IOException,
            URISyntaxException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            InvalidResponseException,
            XmlParserException,
            InternalException
    {
        return jobApplicationService.respondToVacancy(applicant, jobApplicationRequestDto);
    }

    @GetMapping("/applicant/applications")
    public List<JobApplicationResponseDto> findAllByApplicant(
            @AuthenticationPrincipal Applicant applicant) {
        return jobApplicationService.findAllByApplicant(applicant);
    }

    @PutMapping("/applicant/{jobApplicationId}")
    public JobApplicationResponseDto updateJobApplication(
            @PathVariable Long jobApplicationId,
            @ModelAttribute JobApplicationUpdateRequestDto jobApplicationUpdateRequestDto
    ) throws Exception {
        return jobApplicationService.updateJobApplication(jobApplicationId, jobApplicationUpdateRequestDto);
    }

    @DeleteMapping("/applicant/{jobApplicationId}")
    public void deleteJobApplication(
            @AuthenticationPrincipal Applicant applicant,
            @PathVariable Long jobApplicationId
    ) {
        jobApplicationService.deleteJobApplication(applicant, jobApplicationId);
    }

    @PatchMapping("/hr/{jobApplicationId}/schedule-interview")
    public JobApplicationResponseDto scheduleInterview(
            @PathVariable Long jobApplicationId) {
        return jobApplicationService.scheduleInterview(jobApplicationId);
    }

    @PatchMapping("/hr/{jobApplicationId}/reject")
    public JobApplicationResponseDto rejectApplicant(
            @PathVariable Long jobApplicationId) {
        return jobApplicationService.rejectApplicant(jobApplicationId);
    }

    @PatchMapping("/hr/{jobApplicationId}/hire")
    public JobApplicationResponseDto hireApplicant(
            @PathVariable Long jobApplicationId) {
        return jobApplicationService.hireApplicant(jobApplicationId);
    }

    @PatchMapping("/hr/{jobApplicationId}/fail-interview")
    public JobApplicationResponseDto markInterviewAsFailed(
            @PathVariable Long jobApplicationId) {
        return jobApplicationService.markInterviewAsFailed(jobApplicationId);
    }

    @GetMapping("/hr/vacancy/{vacancyId}")
    public List<JobApplicationResponseDto> findAllByVacancy(
            @PathVariable Long vacancyId) {
        return jobApplicationService.findAllByVacancy(vacancyId);
    }
}
