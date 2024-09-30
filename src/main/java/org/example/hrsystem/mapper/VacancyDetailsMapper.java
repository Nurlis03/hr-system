package org.example.hrsystem.mapper;

import org.example.hrsystem.dto.response.VacancyDetailsResponseDto;
import org.example.hrsystem.entity.VacancyDetail;

public interface VacancyDetailsMapper {
    VacancyDetailsResponseDto toVacancyDetailsDto(VacancyDetail vacancyDetail);
}
