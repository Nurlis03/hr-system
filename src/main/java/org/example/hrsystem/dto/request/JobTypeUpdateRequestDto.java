package org.example.hrsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobTypeUpdateRequestDto {
    private Long id;
    private String jobTypeTitleKg;
    private String jobTypeTitleRu;
}
