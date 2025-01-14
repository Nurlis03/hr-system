package org.example.hrsystem.repository;

import org.example.hrsystem.entity.User;
import org.example.hrsystem.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findAllByHrSpecialist(User user);
}
