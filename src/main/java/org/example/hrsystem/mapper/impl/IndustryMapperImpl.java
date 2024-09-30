package org.example.hrsystem.mapper.impl;

import org.example.hrsystem.dto.request.IndustryCreateRequestDto;
import org.example.hrsystem.dto.response.IndustryResponseDto;
import org.example.hrsystem.entity.Industry;
import org.example.hrsystem.mapper.IndustryMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndustryMapperImpl implements IndustryMapper {

    @Override
    public IndustryResponseDto toIndustryDto(Industry industry) {
        return IndustryResponseDto.builder()
                .id(industry.getId())
                .industryTitleKg(industry.getIndustryTitleKg())
                .industryTitleRu(industry.getIndustryTitleRu())
                .build();
    }

    @Override
    public List<IndustryResponseDto> toIndustryDto(List<Industry> industries) {
        return industries
                .stream()
                .map(this::toIndustryDto)
                .toList();
    }

    @Override
    public Industry toIndustry(IndustryCreateRequestDto createRequestDto) {
        return Industry
                .builder()
                .industryTitleKg(createRequestDto.getIndustryTitleKg())
                .industryTitleRu(createRequestDto.getIndustryTitleRu())
                .build();
    }
}
