package org.example.hrsystem.dto.request;

import lombok.Getter;

@Getter
public class VacancyCommentCreateRequestDto {
    private Long vacancyId;
    private String commentText;
}
