package org.example.hrsystem.service.impl;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.WorkScheduleCreateRequestDto;
import org.example.hrsystem.dto.request.WorkScheduleUpdateRequestDto;
import org.example.hrsystem.dto.response.WorkScheduleResponseDto;
import org.example.hrsystem.entity.WorkSchedule;
import org.example.hrsystem.exception.NotFoundException;
import org.example.hrsystem.mapper.WorkScheduleMapper;
import org.example.hrsystem.repository.WorkScheduleRepository;
import org.example.hrsystem.service.WorkScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkScheduleServiceImpl implements WorkScheduleService {

    private final WorkScheduleRepository workScheduleRepository;
    private final WorkScheduleMapper workScheduleMapper;

    @Override
    public List<WorkScheduleResponseDto> findAllWorkSchedules() {
        return workScheduleMapper.toWorkScheduleDto(
                workScheduleRepository.findAll()
        );
    }

    @Override
    public WorkScheduleResponseDto findWorkScheduleById(Long id) {
        WorkSchedule workSchedule = workScheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("WorkSchedule not found"));
        return workScheduleMapper.toWorkScheduleDto(workSchedule);
    }

    @Override
    public WorkScheduleResponseDto createWorkSchedule(WorkScheduleCreateRequestDto createRequestDto) {
        WorkSchedule workSchedule = workScheduleMapper.toWorkSchedule(createRequestDto);
        workScheduleRepository.save(workSchedule);
        return workScheduleMapper.toWorkScheduleDto(workSchedule);
    }

    @Override
    public WorkScheduleResponseDto updateWorkSchedule(WorkScheduleUpdateRequestDto updateRequestDto) {
        WorkSchedule workSchedule = workScheduleRepository.findById(updateRequestDto.getId())
                .orElseThrow(() -> new NotFoundException("WorkSchedule not found"));

        workSchedule.setWorkScheduleTitleKg(updateRequestDto.getWorkScheduleTitleKg());
        workSchedule.setWorkScheduleTitleRu(updateRequestDto.getWorkScheduleTitleRu());

        workScheduleRepository.save(workSchedule);
        return workScheduleMapper.toWorkScheduleDto(workSchedule);
    }

    @Override
    public void deleteWorkSchedule(Long id) {
        workScheduleRepository.deleteById(id);
    }
}
