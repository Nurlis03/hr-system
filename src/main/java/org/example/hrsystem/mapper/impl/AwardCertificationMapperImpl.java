package org.example.hrsystem.mapper.impl;

import org.example.hrsystem.dto.response.AwardCertificationResponseDto;
import org.example.hrsystem.entity.AwardCertification;
import org.example.hrsystem.mapper.AwardCertificationMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AwardCertificationMapperImpl implements AwardCertificationMapper {
    @Override
    public AwardCertificationResponseDto toAwardCertificationMapperResponseDto(AwardCertification awardCertification) {
        return AwardCertificationResponseDto
                .builder()
                .awardCertificationTitleRu(awardCertification.getAwardCertificationTitleRu())
                .awardCertificationTitleKg(awardCertification.getAwardCertificationTitleKg())
                .issueDate(awardCertification.getIssueDate())
                .description(awardCertification.getDescription())
                .build();

    }

    @Override
    public List<AwardCertificationResponseDto> toAwardCertificationMapperResponseDto(List<AwardCertification> awardCertifications) {
        return awardCertifications
                .stream()
                .map(this::toAwardCertificationMapperResponseDto)
                .toList();
    }
}
