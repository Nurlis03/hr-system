package org.example.hrsystem.service.impl;

import io.minio.errors.*;
import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.JobApplicationRequestDto;
import org.example.hrsystem.dto.request.JobApplicationUpdateRequestDto;
import org.example.hrsystem.dto.response.JobApplicationResponseDto;
import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.entity.JobApplication;
import org.example.hrsystem.entity.Vacancy;
import org.example.hrsystem.enums.ApplicationStatus;
import org.example.hrsystem.exception.EmptyFileException;
import org.example.hrsystem.exception.NotFoundException;
import org.example.hrsystem.mapper.JobApplicationMapper;
import org.example.hrsystem.repository.JobApplicationRepository;
import org.example.hrsystem.repository.VacancyRepository;
import org.example.hrsystem.service.JobApplicationService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final VacancyRepository vacancyRepository;
    private final JobApplicationMapper jobApplicationMapper;
    private final MinIOServiceImpl minIOService;

    @Override
    public JobApplicationResponseDto respondToVacancy(
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
            InternalException
    {
        Vacancy vacancy = vacancyRepository.findById(jobApplicationRequestDto.getVacancyId())
                .orElseThrow(() -> new NotFoundException(
                        "Vacancy with id " + jobApplicationRequestDto.getVacancyId() + " not found")
                );

        if (jobApplicationRequestDto.getResume().isEmpty()) {
            throw new EmptyFileException("The uploaded file is empty!");
        }

        String resumeUrl = minIOService.uploadFile(
                    jobApplicationRequestDto.getResume().getInputStream(),
                    jobApplicationRequestDto.getResume().getOriginalFilename()
        );

        JobApplication jobApplication = jobApplicationMapper.toJobApplication(
                applicant,
                vacancy,
                jobApplicationRequestDto.getCoverLetter(),
                resumeUrl,
                ApplicationStatus.NEW_APPLICATION
        );
        jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.toJobApplicationDto(jobApplication);
    }

    @Override
    public JobApplicationResponseDto scheduleInterview(Long jobApplicationId) {
        JobApplication jobApplication = findJobApplication(jobApplicationId);

        jobApplication.setApplicationStatus(ApplicationStatus.INTERVIEW_SCHEDULED);
        jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.toJobApplicationDto(jobApplication);
    }

    @Override
    public JobApplicationResponseDto rejectApplicant(Long jobApplicationId) {
        JobApplication jobApplication = findJobApplication(jobApplicationId);

        jobApplication.setApplicationStatus(ApplicationStatus.REJECTED);
        jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.toJobApplicationDto(jobApplication);
    }

    @Override
    public JobApplicationResponseDto hireApplicant(Long jobApplicationId) {
        JobApplication jobApplication = findJobApplication(jobApplicationId);

        jobApplication.setApplicationStatus(ApplicationStatus.HIRED);
        jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.toJobApplicationDto(jobApplication);
    }

    @Override
    public JobApplicationResponseDto markInterviewAsFailed(Long jobApplicationId) {
        JobApplication jobApplication = findJobApplication(jobApplicationId);

        jobApplication.setApplicationStatus(ApplicationStatus.INTERVIEW_FAILED);
        jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.toJobApplicationDto(jobApplication);
    }

    @Override
    public List<JobApplicationResponseDto> findAllByVacancy(Long vacancyId) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new NotFoundException("Vacancy with id " + vacancyId + " not found"));

        return jobApplicationMapper.toJobApplicationDto(
                jobApplicationRepository.findAllByVacancy(vacancy)
        );
    }

    @Override
    public List<JobApplicationResponseDto> findAllByApplicant(Applicant applicant) {
        return jobApplicationMapper.toJobApplicationDto(
                jobApplicationRepository.findAllByApplicant(applicant)
        );
    }

    @Override
    public void deleteJobApplication(Applicant applicant, Long jobApplicationId) {
        JobApplication jobApplication = findJobApplication(jobApplicationId);
        jobApplication.setDeletedBy(applicant);
        jobApplication.setDeletedAt(LocalDateTime.now());
        jobApplicationRepository.save(jobApplication);
    }

    @Override
    public JobApplicationResponseDto updateJobApplication(
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
            InternalException
    {
        JobApplication jobApplication = findJobApplication(jobApplicationId);

        jobApplication.setCoverLetter(jobApplicationUpdateRequestDto.getCoverLetter());

        if (jobApplicationUpdateRequestDto.getResume() != null && !jobApplicationUpdateRequestDto.getResume().isEmpty()) {
            String oldObjectName = extractObjectNameFromUrl(jobApplication.getResumeUrl());
            minIOService.deleteFile(oldObjectName);

            String newResumeUrl = minIOService.uploadFile(
                    jobApplicationUpdateRequestDto.getResume().getInputStream(),
                    jobApplicationUpdateRequestDto.getResume().getOriginalFilename()
            );
            jobApplication.setResumeUrl(newResumeUrl);
        }

        jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.toJobApplicationDto(jobApplication);
    }

    private JobApplication findJobApplication(Long jobApplicationId) {
        return jobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() -> new NotFoundException("Job application with id " + jobApplicationId + " not found"));
    }

    private String extractObjectNameFromUrl(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String[] segments = uri.getPath().split("/");
        return segments[segments.length - 1]; // Get the last part which is the object name
    }
}
