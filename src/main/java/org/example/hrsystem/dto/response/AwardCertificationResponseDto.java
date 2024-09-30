package org.example.hrsystem.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Builder
public class AwardCertificationResponseDto {
    private String awardCertificationTitleRu;
    private String awardCertificationTitleKg;

    private Date issueDate;
    private String description;
}
