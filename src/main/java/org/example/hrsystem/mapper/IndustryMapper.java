package org.example.hrsystem.mapper;

import org.example.hrsystem.dto.request.IndustryCreateRequestDto;
import org.example.hrsystem.dto.response.IndustryResponseDto;
import org.example.hrsystem.entity.Industry;

import java.util.List;

public interface IndustryMapper {

    IndustryResponseDto toIndustryDto(Industry industry);

    List<IndustryResponseDto> toIndustryDto(List<Industry> industries);

    Industry toIndustry(IndustryCreateRequestDto createRequestDto);
}
