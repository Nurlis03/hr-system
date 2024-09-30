package org.example.hrsystem.service.impl;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.VacancyCommentCreateRequestDto;
import org.example.hrsystem.dto.request.VacancyCommentUpdateRequestDto;
import org.example.hrsystem.dto.response.VacancyCommentResponseDto;
import org.example.hrsystem.entity.User;
import org.example.hrsystem.entity.Vacancy;
import org.example.hrsystem.entity.VacancyComment;
import org.example.hrsystem.exception.NotFoundException;
import org.example.hrsystem.mapper.VacancyCommentMapper;
import org.example.hrsystem.repository.VacancyCommentRepository;
import org.example.hrsystem.repository.VacancyRepository;
import org.example.hrsystem.service.VacancyCommentService;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class VacancyCommentServiceImpl implements VacancyCommentService {
    private final VacancyCommentMapper vacancyCommentMapper;
    private final VacancyCommentRepository vacancyCommentRepository;
    private final VacancyRepository vacancyRepository;

    @Override
    public List<VacancyCommentResponseDto> findAllByUser(User user) {
        return vacancyCommentMapper.toVacancyCommentDto(
                vacancyCommentRepository.findAllByUser(user)
        );
    }

    @Override
    public List<VacancyCommentResponseDto> findAllByVacancy(Long id) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vacancy with id " + id + " not found"));

        return vacancyCommentMapper.toVacancyCommentDto(
                vacancyCommentRepository.findAllByVacancy(vacancy)
        );
    }

    @Override
    public VacancyCommentResponseDto findVacancyCommentById(Long VacancyCommentId) {
        VacancyComment vacancyComment = vacancyCommentRepository.findById(VacancyCommentId)
                .orElseThrow(() -> new NotFoundException("Vacancy comment with id " + VacancyCommentId + " not found")
                );
        return vacancyCommentMapper.toVacancyCommentDto(vacancyComment);
    }

    @Override
    public VacancyCommentResponseDto createVacancyComment(
            User user,
            VacancyCommentCreateRequestDto vacancyCommentCreateRequestDto
    ) {
        Vacancy vacancy = vacancyRepository
                .findById(vacancyCommentCreateRequestDto.getVacancyId())
                .orElseThrow(() -> new NotFoundException(
                        "Vacancy with id " + vacancyCommentCreateRequestDto.getVacancyId() + " not found"
                ));
        VacancyComment newVacancyComment = vacancyCommentMapper.toVacancyComment(
                vacancy,
                user,
                vacancyCommentCreateRequestDto
        );
        vacancyCommentRepository.save(newVacancyComment);
        return vacancyCommentMapper.toVacancyCommentDto(newVacancyComment);
    }

    @Override
    public VacancyCommentResponseDto updateVacancyComment(
            VacancyCommentUpdateRequestDto vacancyCommentUpdateRequestDto
    ) {
        VacancyComment vacancyComment = vacancyCommentRepository
                .findById(vacancyCommentUpdateRequestDto.getId())
                .orElseThrow(() -> new NotFoundException(
                        "Vacancy comment with id " + vacancyCommentUpdateRequestDto.getId() + " not found")
                );
        vacancyComment.setCommentText(vacancyCommentUpdateRequestDto.getCommentText());

        vacancyCommentRepository.save(vacancyComment);
        return vacancyCommentMapper.toVacancyCommentDto(vacancyComment);
    }

    @Override
    public void deleteVacancyCommentById(
            User user,
            Long vacancyId
    ) {
        VacancyComment vacancyComment = vacancyCommentRepository
                .findById(vacancyId)
                .orElseThrow(() -> new NotFoundException(
                        "Vacancy comment with id " + vacancyId + " not found")
                );

        vacancyComment.setDeletedBy(user);
        vacancyComment.setDeletedAt(LocalDateTime.now());

        vacancyCommentRepository.save(vacancyComment);
    }
}
