package org.example.hrsystem.mapper;

import org.example.hrsystem.dto.request.VacancyCommentCreateRequestDto;
import org.example.hrsystem.dto.response.VacancyCommentResponseDto;
import org.example.hrsystem.entity.User;
import org.example.hrsystem.entity.Vacancy;
import org.example.hrsystem.entity.VacancyComment;

import java.util.List;

public interface VacancyCommentMapper {
    List<VacancyCommentResponseDto> toVacancyCommentDto(List<VacancyComment> vacancyComments);
    VacancyCommentResponseDto toVacancyCommentDto(VacancyComment vacancyComment);
    VacancyComment toVacancyComment(
            Vacancy vacancy,
            User user,
            VacancyCommentCreateRequestDto vacancyCommentCreateRequestDto
    );
}
