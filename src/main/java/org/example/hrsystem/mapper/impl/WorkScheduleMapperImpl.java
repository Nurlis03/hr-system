package org.example.hrsystem.mapper.impl;

import org.example.hrsystem.dto.request.WorkScheduleCreateRequestDto;
import org.example.hrsystem.dto.response.WorkScheduleResponseDto;
import org.example.hrsystem.entity.WorkSchedule;
import org.example.hrsystem.mapper.WorkScheduleMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkScheduleMapperImpl implements WorkScheduleMapper {

    @Override
    public WorkScheduleResponseDto toWorkScheduleDto(WorkSchedule workSchedule) {
        return WorkScheduleResponseDto.builder()
                .id(workSchedule.getId())
                .workScheduleTitleKg(workSchedule.getWorkScheduleTitleKg())
                .workScheduleTitleRu(workSchedule.getWorkScheduleTitleRu())
                .build();
    }

    @Override
    public List<WorkScheduleResponseDto> toWorkScheduleDto(List<WorkSchedule> workSchedules) {
        return workSchedules
                .stream()
                .map(this::toWorkScheduleDto)
                .toList();
    }

    @Override
    public WorkSchedule toWorkSchedule(WorkScheduleCreateRequestDto createRequestDto) {
        return WorkSchedule.builder()
                .workScheduleTitleKg(createRequestDto.getWorkScheduleTitleKg())
                .workScheduleTitleRu(createRequestDto.getWorkScheduleTitleRu())
                .build();
    }
}
