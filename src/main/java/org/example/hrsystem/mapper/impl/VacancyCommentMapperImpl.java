package org.example.hrsystem.mapper.impl;

import org.example.hrsystem.dto.request.VacancyCommentCreateRequestDto;
import org.example.hrsystem.dto.response.VacancyCommentResponseDto;
import org.example.hrsystem.entity.User;
import org.example.hrsystem.entity.Vacancy;
import org.example.hrsystem.entity.VacancyComment;
import org.example.hrsystem.mapper.VacancyCommentMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VacancyCommentMapperImpl implements VacancyCommentMapper {
    @Override
    public List<VacancyCommentResponseDto> toVacancyCommentDto(List<VacancyComment> vacancyComments) {
        if (vacancyComments == null) {
            return List.of();
        }
        return vacancyComments
                        .stream()
                        .map(this::toVacancyCommentDto)
                        .toList();
    }

    @Override
    public VacancyCommentResponseDto toVacancyCommentDto(VacancyComment vacancyComment) {
        return VacancyCommentResponseDto
                .builder()
                .id(vacancyComment.getId())
                .commentText(vacancyComment.getCommentText())
                .vacancyId(vacancyComment.getVacancy().getId())
                .build();
    }

    @Override
    public VacancyComment toVacancyComment(
            Vacancy vacancy,
            User user,
            VacancyCommentCreateRequestDto vacancyCommentCreateRequestDto
    ) {
        return VacancyComment
                .builder()
                .vacancy(vacancy)
                .commentText(vacancyCommentCreateRequestDto.getCommentText())
                .user(user)
                .build();
    }
}
