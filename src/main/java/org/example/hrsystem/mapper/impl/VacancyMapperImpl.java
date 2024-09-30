package org.example.hrsystem.mapper.impl;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.VacancyCreateRequestDto;
import org.example.hrsystem.dto.response.VacancyCommentResponseDto;
import org.example.hrsystem.dto.response.VacancyDetailsResponseDto;
import org.example.hrsystem.dto.response.VacancyResponseDto;
import org.example.hrsystem.entity.*;
import org.example.hrsystem.enums.VacancyStatus;
import org.example.hrsystem.mapper.VacancyCommentMapper;
import org.example.hrsystem.mapper.VacancyDetailsMapper;
import org.example.hrsystem.mapper.VacancyMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VacancyMapperImpl implements VacancyMapper {
    private final VacancyCommentMapper vacancyCommentMapper;
    private final VacancyDetailsMapper vacancyDetailsMapper;

    @Override
    public VacancyResponseDto toVacancyDto(Vacancy vacancy) {
        List<VacancyCommentResponseDto> vacancyCommentResponseDtoList =
                vacancyCommentMapper.toVacancyCommentDto(vacancy.getVacancyComments());

        VacancyDetailsResponseDto vacancyDetailsResponseDto =
                vacancyDetailsMapper.toVacancyDetailsDto(vacancy.getVacancyDetail());
        return VacancyResponseDto
                .builder()
                .id(vacancy.getId())
                .titleKg(vacancy.getVacancyTitleKg())
                .titleRu(vacancy.getVacancyTitleRu())
                .descriptionKg(vacancy.getDescriptionKg())
                .descriptionRu(vacancy.getDescriptionRu())
                .vacancyStatus(vacancy.getVacancyStatus())
                .educationRequired(vacancy.getEducationRequired())
                .salaryTo(vacancy.getSalaryTo())
                .salaryFrom(vacancy.getSalaryFrom())
                .experienceYearsRequired(vacancy.getExperienceYearsRequired())
                .hrSpecialistId(vacancy.getHrSpecialist().getId())
                .comments(vacancyCommentResponseDtoList)
                .vacancyDetails(vacancyDetailsResponseDto)
                .build();
    }

    @Override
    public VacancyResponseDto toVacancyDtoWithoutComments(Vacancy vacancy) {

        VacancyDetailsResponseDto vacancyDetailsResponseDto =
                vacancyDetailsMapper.toVacancyDetailsDto(vacancy.getVacancyDetail());
        return VacancyResponseDto
                .builder()
                .id(vacancy.getId())
                .titleKg(vacancy.getVacancyTitleKg())
                .titleRu(vacancy.getVacancyTitleRu())
                .descriptionKg(vacancy.getDescriptionKg())
                .descriptionRu(vacancy.getDescriptionRu())
                .vacancyStatus(vacancy.getVacancyStatus())
                .educationRequired(vacancy.getEducationRequired())
                .salaryTo(vacancy.getSalaryTo())
                .salaryFrom(vacancy.getSalaryFrom())
                .experienceYearsRequired(vacancy.getExperienceYearsRequired())
                .hrSpecialistId(vacancy.getHrSpecialist().getId())
                .vacancyDetails(vacancyDetailsResponseDto)
                .build();
    }

    @Override
    public List<VacancyResponseDto> toVacancyDto(List<Vacancy> vacancies) {
        return vacancies
                .stream()
                .map(vacancy -> VacancyResponseDto
                        .builder()
                        .id(vacancy.getId())
                        .titleKg(vacancy.getVacancyTitleKg())
                        .titleRu(vacancy.getVacancyTitleRu())
                        .descriptionKg(vacancy.getDescriptionKg())
                        .descriptionRu(vacancy.getDescriptionRu())
                        .build())
                .toList();
    }

    @Override
    public Vacancy toVacancy(
            JobType jobType,
            WorkSchedule workSchedule,
            Industry industry,
            HrSpecialist hrSpecialist,
            VacancyCreateRequestDto vacancyCreateRequestDto
    ) {

        VacancyDetail vacancyDetail = VacancyDetail
                .builder()
                .jobType(jobType)
                .workSchedule(workSchedule)
                .industry(industry)
                .build();

        return Vacancy
                .builder()
                .vacancyTitleRu(vacancyCreateRequestDto.getTitleRu())
                .vacancyTitleKg(vacancyCreateRequestDto.getTitleKg())
                .descriptionRu(vacancyCreateRequestDto.getDescriptionRu())
                .descriptionKg(vacancyCreateRequestDto.getDescriptionKg())
                .salaryFrom(vacancyCreateRequestDto.getSalaryFrom())
                .salaryTo(vacancyCreateRequestDto.getSalaryTo())
                .experienceYearsRequired(vacancyCreateRequestDto.getExperienceYearsRequired())
                .educationRequired(vacancyCreateRequestDto.getEducationRequired())
                .vacancyStatus(VacancyStatus.RELEVANT)
                .hrSpecialist(hrSpecialist)
                .vacancyDetail(vacancyDetail)
                .build();
    }
}
