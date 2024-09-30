package org.example.hrsystem.service.impl;

import lombok.AllArgsConstructor;
import org.example.hrsystem.dto.request.VacancyCreateRequestDto;
import org.example.hrsystem.dto.request.VacancyUpdateRequestDto;
import org.example.hrsystem.dto.response.VacancyResponseDto;
import org.example.hrsystem.entity.*;
import org.example.hrsystem.enums.ApplicationStatus;
import org.example.hrsystem.enums.VacancyStatus;
import org.example.hrsystem.exception.NotFoundException;
import org.example.hrsystem.mapper.VacancyMapper;
import org.example.hrsystem.repository.*;
import org.example.hrsystem.service.VacancyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;
    private final JobTypeRepository jobTypeRepository;
    private final WorkScheduleRepository workScheduleRepository;
    private final IndustryRepository industryRepository;

    @Override
    public List<VacancyResponseDto> findAllVacancies() {
        return vacancyMapper.toVacancyDto(
                vacancyRepository.findAll()
        );
    }

    @Override
    public List<VacancyResponseDto> findAllByHrSpecialist(HrSpecialist hrSpecialist) {
        return vacancyMapper.toVacancyDto(
                vacancyRepository.findAllByHrSpecialist(hrSpecialist)
        );
    }

    @Override
    public VacancyResponseDto findVacancyById(Long id) {
        return vacancyMapper.toVacancyDto(
                vacancyRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Vacancy with id " + id + " not found"))
        );
    }

    @Override
    public VacancyResponseDto createVacancy(User user, VacancyCreateRequestDto vacancyCreateRequestDto) {
        JobType jobType = findJobType(vacancyCreateRequestDto.getJobTypeId());
        WorkSchedule workSchedule = findWorkSchedule(vacancyCreateRequestDto.getWorkScheduleId());
        Industry industry = findIndustry(vacancyCreateRequestDto.getIndustryId());

        Vacancy newVacancy = vacancyMapper.toVacancy(
                jobType,
                workSchedule,
                industry,
                (HrSpecialist) user,
                vacancyCreateRequestDto
        );
        vacancyRepository.save(newVacancy);
        return vacancyMapper.toVacancyDto(newVacancy);
    }

    @Override
    public VacancyResponseDto updateVacancy(VacancyUpdateRequestDto vacancyUpdateRequestDto) {
        Vacancy vacancy = vacancyRepository.findById(vacancyUpdateRequestDto.getVacancyId())
                .orElseThrow(() -> new NotFoundException("Vacancy with id " + vacancyUpdateRequestDto.getVacancyId() + " not found"));

        VacancyDetail vacancyDetail = vacancy.getVacancyDetail();

        Industry industry = findIndustry(vacancyUpdateRequestDto.getIndustryId());
        JobType jobType = findJobType(vacancyUpdateRequestDto.getJobTypeId());
        WorkSchedule workSchedule = findWorkSchedule(vacancyUpdateRequestDto.getWorkScheduleId());

        vacancyDetail.setIndustry(industry);
        vacancyDetail.setJobType(jobType);
        vacancyDetail.setWorkSchedule(workSchedule);

        vacancy.setDescriptionKg(vacancyUpdateRequestDto.getTitleKg());
        vacancy.setDescriptionRu(vacancyUpdateRequestDto.getTitleRu());
        vacancy.setDescriptionKg(vacancyUpdateRequestDto.getDescriptionKg());
        vacancy.setDescriptionRu(vacancyUpdateRequestDto.getDescriptionRu());
        vacancy.setSalaryFrom(vacancyUpdateRequestDto.getSalaryFrom());
        vacancy.setSalaryTo(vacancyUpdateRequestDto.getSalaryTo());
        vacancy.setExperienceYearsRequired(vacancyUpdateRequestDto.getExperienceYearsRequired());
        vacancy.setEducationRequired(vacancyUpdateRequestDto.getEducationRequired());
        vacancy.setVacancyDetail(vacancyDetail);

        vacancyRepository.save(vacancy);
        return vacancyMapper.toVacancyDto(vacancy);
    }

    @Override
    public void deleteVacancy(HrSpecialist hrSpecialist, Long id) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vacancy with id " + id + " not found"));

        markVacancyAsDeleted(vacancy, hrSpecialist);
        vacancyRepository.save(vacancy);
    }

    private void markVacancyAsDeleted(Vacancy vacancy, User user) {
        vacancy.setVacancyStatus(VacancyStatus.DELETED);
        vacancy.setDeletedAt(LocalDateTime.now());
        vacancy.setDeletedBy(user);

        markVacancyDetailAsDeleted(vacancy.getVacancyDetail(), user);
        markJobApplicationsAsDeleted(vacancy.getJobApplications(), user);
        markVacancyCommentsAsDeleted(vacancy.getVacancyComments(), user);
    }

    private void markVacancyDetailAsDeleted(VacancyDetail vacancyDetail, User user) {
        vacancyDetail.setDeletedBy(user);
        vacancyDetail.setDeletedAt(LocalDateTime.now());
    }

    private void markJobApplicationsAsDeleted(List<JobApplication> jobApplications, User user) {
        jobApplications.forEach(jobApplication -> {
            jobApplication.setDeletedBy(user);
            jobApplication.setDeletedAt(LocalDateTime.now());
            jobApplication.setApplicationStatus(ApplicationStatus.DELETED);
        });
    }

    private void markVacancyCommentsAsDeleted(List<VacancyComment> vacancyComments, User user) {
        vacancyComments.forEach(vacancyComment -> {
            vacancyComment.setDeletedBy(user);
            vacancyComment.setDeletedAt(LocalDateTime.now());
        });
    }

    private JobType findJobType(Long jobTypeId) {
        return jobTypeRepository.findById(jobTypeId)
                .orElseThrow(() -> new NotFoundException("Job type with id " + jobTypeId + " not found"));
    }

    private WorkSchedule findWorkSchedule(Long workScheduleId) {
        return workScheduleRepository.findById(workScheduleId)
                .orElseThrow(() -> new NotFoundException("Work Schedule with id " + workScheduleId + " not found"));
    }

    private Industry findIndustry(Long industryId) {
        return industryRepository.findById(industryId)
                .orElseThrow(() -> new NotFoundException("Industry with id " + industryId + " not found"));
    }
}
