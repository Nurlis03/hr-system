package org.example.hrsystem.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.hrsystem.enums.ApplicationStatus;


@Getter
@Setter
@Builder
public class JobApplicationResponseDto {
    private VacancyResponseDto vacancy;
    private String coverLetter;
    private ApplicationStatus applicationStatus;
}