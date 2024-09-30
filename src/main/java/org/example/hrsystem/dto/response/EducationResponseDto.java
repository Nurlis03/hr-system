package org.example.hrsystem.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.hrsystem.entity.Applicant;

import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Builder
public class EducationResponseDto {
    private Date eduFrom;
    private Date eduTo;

    private String educationTitleKg;
    private String educationTitleRu;

    private String degreeKg;
    private String degreeRu;

    private String gpa;
}
