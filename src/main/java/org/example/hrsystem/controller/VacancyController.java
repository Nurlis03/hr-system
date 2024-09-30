package org.example.hrsystem.controller;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.VacancyCreateRequestDto;
import org.example.hrsystem.dto.request.VacancyUpdateRequestDto;
import org.example.hrsystem.dto.response.VacancyResponseDto;
import org.example.hrsystem.entity.HrSpecialist;
import org.example.hrsystem.service.VacancyService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancies")
@AllArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping
    public List<VacancyResponseDto> getAllVacancies() {
        return vacancyService.findAllVacancies();
    }

    @GetMapping("/hr-specialist")
    public List<VacancyResponseDto> getAllByHrSpecialist(@AuthenticationPrincipal HrSpecialist hrSpecialist) {
        return vacancyService.findAllByHrSpecialist(hrSpecialist);
    }

    @GetMapping("/{id}")
    public VacancyResponseDto getVacancyById(@PathVariable Long id) {
        return vacancyService.findVacancyById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VacancyResponseDto createVacancy(
            @AuthenticationPrincipal HrSpecialist hrSpecialist,
            @RequestBody VacancyCreateRequestDto vacancyCreateRequestDto
    ) {
        return vacancyService.createVacancy(hrSpecialist, vacancyCreateRequestDto);
    }

    @PutMapping
    public VacancyResponseDto updateVacancy(
            @RequestBody VacancyUpdateRequestDto vacancyUpdateRequestDto) {
        return vacancyService.updateVacancy(vacancyUpdateRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteVacancy(@AuthenticationPrincipal HrSpecialist hrSpecialist, @PathVariable Long id) {
        vacancyService.deleteVacancy(hrSpecialist, id);
    }
}
