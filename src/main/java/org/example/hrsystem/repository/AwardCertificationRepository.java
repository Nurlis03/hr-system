package org.example.hrsystem.repository;

import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.entity.AwardCertification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AwardCertificationRepository extends JpaRepository<AwardCertification, Long> {
}
