package org.example.hrsystem.repository;

import org.example.hrsystem.entity.User;
import org.example.hrsystem.entity.Vacancy;
import org.example.hrsystem.entity.VacancyComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyCommentRepository extends JpaRepository<VacancyComment, Long> {
    List<VacancyComment> findAllByUser(User user);
    List<VacancyComment> findAllByVacancy(Vacancy vacancy);
}
