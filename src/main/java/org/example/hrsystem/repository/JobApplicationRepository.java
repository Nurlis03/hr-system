package org.example.hrsystem.repository;

import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.entity.JobApplication;
import org.example.hrsystem.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findAllByApplicant(Applicant applicant);
    List<JobApplication> findAllByVacancy(Vacancy vacancy);
}
