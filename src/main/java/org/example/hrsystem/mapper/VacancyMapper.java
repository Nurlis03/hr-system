package org.example.hrsystem.mapper;

import org.example.hrsystem.dto.request.VacancyCreateRequestDto;
import org.example.hrsystem.dto.response.VacancyResponseDto;
import org.example.hrsystem.entity.*;

import java.util.List;

public interface VacancyMapper {
    VacancyResponseDto toVacancyDto(Vacancy vacancy);
    VacancyResponseDto toVacancyDtoWithoutComments(Vacancy vacancy) ;
    List<VacancyResponseDto> toVacancyDto(List<Vacancy> vacancies);
    Vacancy toVacancy(
            JobType jobType,
            WorkSchedule workSchedule,
            Industry industry,
            HrSpecialist hrSpecialist,
            VacancyCreateRequestDto vacancyCreateRequestDto
    );
}
