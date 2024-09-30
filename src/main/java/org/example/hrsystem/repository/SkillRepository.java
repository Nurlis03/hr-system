package org.example.hrsystem.repository;

import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
