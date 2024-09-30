package org.example.hrsystem.mapper;

import org.example.hrsystem.dto.request.WorkScheduleCreateRequestDto;
import org.example.hrsystem.dto.response.WorkScheduleResponseDto;
import org.example.hrsystem.entity.WorkSchedule;

import java.util.List;

public interface WorkScheduleMapper {

    WorkScheduleResponseDto toWorkScheduleDto(WorkSchedule workSchedule);

    List<WorkScheduleResponseDto> toWorkScheduleDto(List<WorkSchedule> workSchedule);

    WorkSchedule toWorkSchedule(WorkScheduleCreateRequestDto createRequestDto);
}
