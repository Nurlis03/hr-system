package org.example.hrsystem.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class VacancyCommentResponseDto {
    private Long id;
    private String commentText;
    private Long vacancyId;
}
