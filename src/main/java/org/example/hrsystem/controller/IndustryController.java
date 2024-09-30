package org.example.hrsystem.controller;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.IndustryCreateRequestDto;
import org.example.hrsystem.dto.request.IndustryUpdateRequestDto;
import org.example.hrsystem.dto.response.IndustryResponseDto;
import org.example.hrsystem.service.IndustryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/industries")
@AllArgsConstructor
public class IndustryController {
    private final IndustryService industryService;

    @GetMapping
    public List<IndustryResponseDto> getAllIndustries() {
        return industryService.findAllIndustries();
    }

    @GetMapping("/{id}")
    public IndustryResponseDto getIndustryById(@PathVariable Long id) {
        return industryService.findIndustryById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public IndustryResponseDto createIndustry(@RequestBody IndustryCreateRequestDto industryCreateRequestDto) {
        return industryService.createIndustry(industryCreateRequestDto);
    }

    @PutMapping
    public IndustryResponseDto updateIndustry(
            @RequestBody IndustryUpdateRequestDto industryUpdateRequestDto) {
        return industryService.updateIndustry(industryUpdateRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteIndustry(@PathVariable Long id) {
        industryService.deleteIndustry(id);
    }
}
