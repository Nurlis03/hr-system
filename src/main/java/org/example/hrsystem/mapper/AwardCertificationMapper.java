package org.example.hrsystem.mapper;

import org.example.hrsystem.dto.response.AwardCertificationResponseDto;
import org.example.hrsystem.entity.AwardCertification;

import java.util.List;

public interface AwardCertificationMapper {
    AwardCertificationResponseDto toAwardCertificationMapperResponseDto(AwardCertification awardCertification);
    List<AwardCertificationResponseDto> toAwardCertificationMapperResponseDto(List<AwardCertification> awardCertifications);
}
