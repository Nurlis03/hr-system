package org.example.hrsystem.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.hrsystem.entity.base.BaseEntity;
import org.example.hrsystem.entity.base.SoftDeletable;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@Audited
@Where(clause = "deleted_at IS NULL")
public class Education extends SoftDeletable {

    private Date eduFrom;
    private Date eduTo;

    private String educationTitleKg;
    private String educationTitleRu;

    private String degreeKg;
    private String degreeRu;

    private String gpa;

    @ManyToOne
    private Applicant applicant;
}
