package org.example.hrsystem.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.entity.Company;
import org.example.hrsystem.entity.JobType;
import org.example.hrsystem.entity.Position;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@Builder
public class WorkExperienceResponseDto {
    private Date expFrom;
    private Date expTo;

    private String expDescription;

    private String positionTitleRu;
    private String positionTitleKg;

    private String jobTypeTitleKg;
    private String jobTypeTitleRu;

    private String companyTitleRu;
    private String companyTitleKg;
}
