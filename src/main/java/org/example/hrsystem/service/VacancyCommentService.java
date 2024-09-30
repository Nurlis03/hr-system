package org.example.hrsystem.service;

import org.example.hrsystem.dto.request.VacancyCommentCreateRequestDto;
import org.example.hrsystem.dto.request.VacancyCommentUpdateRequestDto;
import org.example.hrsystem.dto.response.VacancyCommentResponseDto;
import org.example.hrsystem.entity.User;

import java.util.List;

public interface VacancyCommentService {
    List<VacancyCommentResponseDto> findAllByUser(User user);
    List<VacancyCommentResponseDto> findAllByVacancy(Long id);
    VacancyCommentResponseDto findVacancyCommentById(Long VacancyCommentId);
    VacancyCommentResponseDto createVacancyComment(
            User user,
            VacancyCommentCreateRequestDto vacancyCommentCreateRequestDto
    );
    VacancyCommentResponseDto updateVacancyComment(
            VacancyCommentUpdateRequestDto vacancyCommentUpdateRequestDto
    );
    void deleteVacancyCommentById(
            User user,
            Long vacancyId
    );
}
