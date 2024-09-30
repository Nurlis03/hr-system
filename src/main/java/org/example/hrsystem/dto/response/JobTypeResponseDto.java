package org.example.hrsystem.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JobTypeResponseDto {
    private Long id;
    private String jobTypeTitleKg;
    private String jobTypeTitleRu;
}
