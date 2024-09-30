package org.example.hrsystem.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VacancyDetailsResponseDto {
    private String workScheduleTitleKg;
    private String workScheduleTitleRu;

    private String industryTitleKg;
    private String industryTitleRu;

    private String jobTypeTitleKg;
    private String jobTypeTitleRu;
}
