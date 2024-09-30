package org.example.hrsystem.service;

import org.example.hrsystem.dto.request.IndustryCreateRequestDto;
import org.example.hrsystem.dto.request.IndustryUpdateRequestDto;
import org.example.hrsystem.dto.response.IndustryResponseDto;

import java.util.List;

public interface IndustryService {

    List<IndustryResponseDto> findAllIndustries();

    IndustryResponseDto findIndustryById(Long id);

    IndustryResponseDto createIndustry(IndustryCreateRequestDto industryCreateRequestDto);

    IndustryResponseDto updateIndustry(IndustryUpdateRequestDto industryUpdateRequestDto);

    void deleteIndustry(Long id);
}
