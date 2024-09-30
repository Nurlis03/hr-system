package org.example.hrsystem.service;

import org.example.hrsystem.dto.request.VacancyCreateRequestDto;
import org.example.hrsystem.dto.request.VacancyUpdateRequestDto;
import org.example.hrsystem.dto.response.VacancyResponseDto;
import org.example.hrsystem.entity.*;

import java.util.List;

public interface VacancyService {
    List<VacancyResponseDto> findAllVacancies();
    List<VacancyResponseDto> findAllByHrSpecialist(HrSpecialist hrSpecialist);
    VacancyResponseDto findVacancyById(Long id);
    VacancyResponseDto createVacancy(User user, VacancyCreateRequestDto vacancyCreateRequestDto);
    VacancyResponseDto updateVacancy(VacancyUpdateRequestDto vacancyUpdateRequestDto);
    void deleteVacancy(HrSpecialist hrSpecialist, Long id);
}
