package org.example.hrsystem.repository;

import org.example.hrsystem.entity.Applicant;
import org.example.hrsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Optional<User> findByEmail(String email);
}
