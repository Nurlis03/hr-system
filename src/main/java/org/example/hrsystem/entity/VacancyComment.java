package org.example.hrsystem.entity;

import lombok.*;
import org.example.hrsystem.entity.base.SoftDeletable;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted_at IS NULL")
@Audited
public class VacancyComment extends SoftDeletable {

    @ManyToOne
    private Vacancy vacancy;

    @ManyToOne
    private User user;

    private String commentText;
}
