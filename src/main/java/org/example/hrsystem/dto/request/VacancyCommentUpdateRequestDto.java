package org.example.hrsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacancyCommentUpdateRequestDto {
    private Long id;
    private String commentText;
}
