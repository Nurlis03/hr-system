package org.example.hrsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkScheduleUpdateRequestDto {
    private Long id;
    private String workScheduleTitleKg;
    private String workScheduleTitleRu;
}
