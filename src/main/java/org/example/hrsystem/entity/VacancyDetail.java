package org.example.hrsystem.entity;

import lombok.*;
import org.example.hrsystem.entity.base.SoftDeletable;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at IS NULL")
@Audited
public class VacancyDetail extends SoftDeletable {

    @OneToOne
    private WorkSchedule workSchedule;

    @OneToOne
    private Industry industry;

    @OneToOne
    private JobType jobType;
}
