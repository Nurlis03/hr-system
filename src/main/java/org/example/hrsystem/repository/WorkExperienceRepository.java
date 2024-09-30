package org.example.hrsystem.repository;

import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
    List<WorkExperience> findAllByApplicant(Applicant applicant);
}
