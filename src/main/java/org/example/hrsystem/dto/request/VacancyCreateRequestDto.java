package org.example.hrsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacancyCreateRequestDto {
    private String titleRu;
    private String titleKg;

    private String descriptionRu;
    private String descriptionKg;

    private Long salaryFrom;
    private Long salaryTo;

    private Long experienceYearsRequired;
    private String educationRequired;

    private Long workScheduleId;
    private Long industryId;
    private Long jobTypeId;
}
