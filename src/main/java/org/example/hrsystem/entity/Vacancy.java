package org.example.hrsystem.entity;

import lombok.*;
import org.example.hrsystem.entity.base.SoftDeletable;
import org.example.hrsystem.enums.VacancyStatus;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at IS NULL")
@Audited
public class Vacancy extends SoftDeletable {

    private String vacancyTitleRu;
    private String vacancyTitleKg;

    private String descriptionRu;
    private String descriptionKg;

    private Long salaryFrom;
    private Long salaryTo;

    private Long experienceYearsRequired;
    private String educationRequired;

    @Enumerated(EnumType.STRING)
    private VacancyStatus vacancyStatus;

    @ManyToOne
    private HrSpecialist hrSpecialist;

    @OneToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private VacancyDetail vacancyDetail;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "vacancy"
    )
    private List<VacancyComment> vacancyComments;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "vacancy"
    )
    private List<JobApplication> jobApplications;
}
