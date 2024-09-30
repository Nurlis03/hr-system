package org.example.hrsystem.mapper.impl;

import org.example.hrsystem.dto.response.VacancyDetailsResponseDto;
import org.example.hrsystem.entity.VacancyDetail;
import org.example.hrsystem.mapper.VacancyDetailsMapper;
import org.springframework.stereotype.Component;

@Component
public class VacancyDetailsMapperImpl implements VacancyDetailsMapper {
    @Override
    public VacancyDetailsResponseDto toVacancyDetailsDto(VacancyDetail vacancyDetail) {
        return VacancyDetailsResponseDto
                .builder()
                .industryTitleRu(vacancyDetail
                        .getIndustry()
                        .getIndustryTitleRu()
                )
                .industryTitleKg(vacancyDetail
                        .getIndustry()
                        .getIndustryTitleKg()
                )
                .workScheduleTitleRu(vacancyDetail
                        .getWorkSchedule()
                        .getWorkScheduleTitleRu()
                )
                .workScheduleTitleKg(vacancyDetail
                        .getWorkSchedule()
                        .getWorkScheduleTitleKg()
                )
                .jobTypeTitleRu(vacancyDetail
                        .getJobType()
                        .getJobTypeTitleRu()
                )
                .jobTypeTitleKg(vacancyDetail
                        .getJobType()
                        .getJobTypeTitleKg()
                )
                .build();
    }
}
