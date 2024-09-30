package org.example.hrsystem.service;

import io.minio.errors.*;
import org.example.hrsystem.dto.request.JobApplicationRequestDto;
import org.example.hrsystem.dto.request.JobApplicationUpdateRequestDto;
import org.example.hrsystem.dto.response.JobApplicationResponseDto;
import org.example.hrsystem.entity.Applicant;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface JobApplicationService {
    JobApplicationResponseDto respondToVacancy(
            Applicant applicant,
            JobApplicationRequestDto jobApplicationRequestDto
    ) throws IOException,
            ServerException,
            InsufficientDataException,
            ErrorResponseException,
            URISyntaxException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            InvalidResponseException,
            XmlParserException,
            InternalException;
    JobApplicationResponseDto scheduleInterview(Long jobApplicationId);
    JobApplicationResponseDto rejectApplicant(Long jobApplicationId);
    JobApplicationResponseDto hireApplicant(Long jobApplicationId);
    JobApplicationResponseDto markInterviewAsFailed(Long jobApplicationId);
    List<JobApplicationResponseDto> findAllByVacancy(Long vacancyId);
    List<JobApplicationResponseDto> findAllByApplicant(Applicant applicant);
    void deleteJobApplication(Applicant applicant, Long jobApplicationId);
    JobApplicationResponseDto updateJobApplication(
        Long jobApplicationId,
        JobApplicationUpdateRequestDto jobApplicationUpdateRequestDto
    ) throws URISyntaxException,
             ServerException,
             InsufficientDataException,
             ErrorResponseException,
             IOException,
             NoSuchAlgorithmException,
             InvalidKeyException,
             InvalidResponseException,
             XmlParserException,
             InternalException ;
}
