package org.example.hrsystem.mapper.impl;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.response.JobApplicationResponseDto;
import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.entity.JobApplication;
import org.example.hrsystem.entity.Vacancy;
import org.example.hrsystem.enums.ApplicationStatus;
import org.example.hrsystem.mapper.JobApplicationMapper;
import org.example.hrsystem.mapper.VacancyMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class JobApplicationMapperImpl implements JobApplicationMapper {
    private final VacancyMapper vacancyMapper;

    @Override
    public JobApplicationResponseDto toJobApplicationDto(JobApplication jobApplication) {
        return JobApplicationResponseDto
                .builder()
                .applicationStatus(jobApplication.getApplicationStatus())
                .coverLetter(jobApplication.getCoverLetter())
                .vacancy(
                        vacancyMapper.toVacancyDtoWithoutComments(jobApplication.getVacancy())
                )
                .build();
    }

    @Override
    public List<JobApplicationResponseDto> toJobApplicationDto(List<JobApplication> jobApplicationList) {
        return jobApplicationList
                .stream()
                .map(this::toJobApplicationDto)
                .toList();
    }

    @Override
    public JobApplication toJobApplication(
            Applicant applicant,
            Vacancy vacancy,
            String coverLetter,
            String resumeUrl,
            ApplicationStatus applicationStatus
    ) {
        return JobApplication
                .builder()
                .applicant(applicant)
                .vacancy(vacancy)
                .coverLetter(coverLetter)
                .resumeUrl(resumeUrl)
                .applicationStatus(applicationStatus)
                .build();
    }
}
