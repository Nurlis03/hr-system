package org.example.hrsystem.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class IndustryResponseDto {
    private Long id;
    private String industryTitleKg;
    private String industryTitleRu;
}