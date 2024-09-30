package org.example.hrsystem.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.hrsystem.entity.base.BaseEntity;
import org.example.hrsystem.entity.base.SoftDeletable;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@Audited
@Where(clause = "deleted_at IS NULL")
public class WorkExperience extends SoftDeletable {
    private Date expFrom;
    private Date expTo;

    private String expDescription;

    @OneToOne
    private Position position;

    @OneToOne
    private JobType jobType;

    @OneToOne
    private Company company;

    @ManyToOne
    private Applicant applicant;
}
