package org.example.hrsystem.entity;

import lombok.*;
import org.example.hrsystem.entity.base.SoftDeletable;
import org.example.hrsystem.enums.ApplicationStatus;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at IS NULL")
@Audited
public class JobApplication extends SoftDeletable {

    @ManyToOne
    private Applicant applicant;

    @ManyToOne
    private Vacancy vacancy;

    private String resumeUrl;

    private String coverLetter;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;
}
