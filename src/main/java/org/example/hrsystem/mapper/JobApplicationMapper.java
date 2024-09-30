package org.example.hrsystem.mapper;

import org.example.hrsystem.dto.response.JobApplicationResponseDto;
import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.entity.JobApplication;
import org.example.hrsystem.entity.Vacancy;
import org.example.hrsystem.enums.ApplicationStatus;

import java.util.List;

public interface JobApplicationMapper {
    JobApplicationResponseDto toJobApplicationDto(JobApplication jobApplication);
    List<JobApplicationResponseDto> toJobApplicationDto(List<JobApplication> jobApplicationList);
    JobApplication toJobApplication(
            Applicant applicant,
            Vacancy vacancy,
            String coverLetter,
            String resumeUrl,
            ApplicationStatus applicationStatus
    );
}
