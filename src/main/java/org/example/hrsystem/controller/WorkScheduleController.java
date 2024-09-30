package org.example.hrsystem.controller;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.WorkScheduleCreateRequestDto;
import org.example.hrsystem.dto.request.WorkScheduleUpdateRequestDto;
import org.example.hrsystem.dto.response.WorkScheduleResponseDto;
import org.example.hrsystem.service.WorkScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/work-schedules")
@AllArgsConstructor
public class WorkScheduleController {

    private final WorkScheduleService workScheduleService;

    @GetMapping
    public List<WorkScheduleResponseDto> getAllWorkSchedules() {
        return workScheduleService.findAllWorkSchedules();
    }

    @GetMapping("/{id}")
    public WorkScheduleResponseDto getWorkScheduleById(@PathVariable Long id) {
        return workScheduleService.findWorkScheduleById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public WorkScheduleResponseDto createWorkSchedule(@RequestBody WorkScheduleCreateRequestDto workScheduleCreateRequestDto) {
        return workScheduleService.createWorkSchedule(workScheduleCreateRequestDto);
    }

    @PutMapping
    public WorkScheduleResponseDto updateWorkSchedule(
            @RequestBody WorkScheduleUpdateRequestDto workScheduleUpdateRequestDto) {
        return workScheduleService.updateWorkSchedule(workScheduleUpdateRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkSchedule(@PathVariable Long id) {
        workScheduleService.deleteWorkSchedule(id);
    }
}
