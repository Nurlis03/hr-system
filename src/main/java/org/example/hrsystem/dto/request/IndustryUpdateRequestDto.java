package org.example.hrsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndustryUpdateRequestDto {
    private Long id;
    private String industryTitleKg;
    private String industryTitleRu;
}