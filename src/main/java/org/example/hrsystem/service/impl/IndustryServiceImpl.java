package org.example.hrsystem.service.impl;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.IndustryCreateRequestDto;
import org.example.hrsystem.dto.request.IndustryUpdateRequestDto;
import org.example.hrsystem.dto.response.IndustryResponseDto;
import org.example.hrsystem.entity.Industry;
import org.example.hrsystem.exception.NotFoundException;
import org.example.hrsystem.mapper.IndustryMapper;
import org.example.hrsystem.repository.IndustryRepository;
import org.example.hrsystem.service.IndustryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class IndustryServiceImpl implements IndustryService {
    private final IndustryRepository industryRepository;
    private final IndustryMapper industryMapper;

    @Override
    public List<IndustryResponseDto> findAllIndustries() {
        return industryMapper.toIndustryDto(
                industryRepository.findAll()
        );
    }

    @Override
    public IndustryResponseDto findIndustryById(Long id) {
        Industry industry = industryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Industry not found with id: " + id));
        return industryMapper.toIndustryDto(industry);
    }

    @Override
    public IndustryResponseDto createIndustry(IndustryCreateRequestDto industryCreateRequestDto) {
        Industry industry = industryMapper.toIndustry(industryCreateRequestDto);
        Industry savedIndustry = industryRepository.save(industry);
        return industryMapper.toIndustryDto(savedIndustry);
    }

    @Override
    public IndustryResponseDto updateIndustry(IndustryUpdateRequestDto industryUpdateRequestDto) {
        Industry existingIndustry = industryRepository.findById(industryUpdateRequestDto.getId())
                .orElseThrow(() -> new NotFoundException("Industry not found with id: " + industryUpdateRequestDto.getId()));

        existingIndustry.setIndustryTitleKg(industryUpdateRequestDto.getIndustryTitleKg());
        existingIndustry.setIndustryTitleRu(industryUpdateRequestDto.getIndustryTitleRu());

        industryRepository.save(existingIndustry);
        return industryMapper.toIndustryDto(existingIndustry);
    }

    @Override
    public void deleteIndustry(Long id) {
        Industry industry = industryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Industry not found with id: " + id));
        industryRepository.delete(industry);
    }
}
