package org.example.hrsystem.service;

import org.example.hrsystem.dto.request.WorkScheduleCreateRequestDto;
import org.example.hrsystem.dto.request.WorkScheduleUpdateRequestDto;
import org.example.hrsystem.dto.response.WorkScheduleResponseDto;

import java.util.List;

public interface WorkScheduleService {
    List<WorkScheduleResponseDto> findAllWorkSchedules();

    WorkScheduleResponseDto findWorkScheduleById(Long id);

    WorkScheduleResponseDto createWorkSchedule(WorkScheduleCreateRequestDto createRequestDto);

    WorkScheduleResponseDto updateWorkSchedule(WorkScheduleUpdateRequestDto updateRequestDto);

    void deleteWorkSchedule(Long id);
}
