package org.example.hrsystem.controller;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.VacancyCommentCreateRequestDto;
import org.example.hrsystem.dto.request.VacancyCommentUpdateRequestDto;
import org.example.hrsystem.dto.response.VacancyCommentResponseDto;
import org.example.hrsystem.entity.User;
import org.example.hrsystem.service.VacancyCommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancy-comments")
@AllArgsConstructor
public class VacancyCommentController {

    private final VacancyCommentService vacancyCommentService;

    @GetMapping("/user")
    public List<VacancyCommentResponseDto> findAllByUser(@AuthenticationPrincipal User user) {
        return vacancyCommentService.findAllByUser(user);
    }

    @GetMapping("/vacancy/{vacancyId}")
    public List<VacancyCommentResponseDto> findAllByVacancy(
            @PathVariable Long vacancyId) {
        return vacancyCommentService.findAllByVacancy(vacancyId);
    }

    @GetMapping("/{vacancyCommentId}")
    public VacancyCommentResponseDto findVacancyCommentById(@PathVariable Long vacancyCommentId) {
        return vacancyCommentService.findVacancyCommentById(vacancyCommentId);
    }

    @PostMapping
    public VacancyCommentResponseDto createVacancyComment(
            @AuthenticationPrincipal User user,
            @RequestBody VacancyCommentCreateRequestDto vacancyCommentCreateRequestDto
    ) {
        return vacancyCommentService.createVacancyComment(user, vacancyCommentCreateRequestDto);
    }

    @PutMapping
    public VacancyCommentResponseDto updateVacancyComment(
            @RequestBody VacancyCommentUpdateRequestDto vacancyCommentUpdateRequestDto
    ) {
        return vacancyCommentService.updateVacancyComment(vacancyCommentUpdateRequestDto);
    }

    @DeleteMapping("/{vacancyCommentId}")
    public void deleteVacancyCommentById(
            @AuthenticationPrincipal User user,
            @PathVariable Long vacancyCommentId
    ) {
        vacancyCommentService.deleteVacancyCommentById(user, vacancyCommentId);
    }
}
