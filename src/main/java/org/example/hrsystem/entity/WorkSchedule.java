package org.example.hrsystem.entity;

import lombok.*;
import org.example.hrsystem.entity.base.BaseEntity;
import org.example.hrsystem.entity.base.SoftDeletable;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
@Where(clause = "deleted_at IS NULL")
public class WorkSchedule extends SoftDeletable {
    private String workScheduleTitleKg;
    private String workScheduleTitleRu;
}
