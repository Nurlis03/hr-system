package org.example.hrsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.hrsystem.enums.VacancyStatus;

import java.util.List;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VacancyResponseDto {
    private Long id;

    private String titleRu;
    private String titleKg;

    private String descriptionRu;
    private String descriptionKg;

    private Long salaryFrom;
    private Long salaryTo;

    private Long experienceYearsRequired;
    private String educationRequired;

    private VacancyStatus vacancyStatus;
    private Long hrSpecialistId;

    private VacancyDetailsResponseDto vacancyDetails;
    private List<VacancyCommentResponseDto> comments;
}