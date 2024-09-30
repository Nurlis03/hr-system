package org.example.hrsystem.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.hrsystem.entity.base.BaseEntity;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Where(clause = "deleted_at IS NULL")
public class Applicant extends User {
    private String firstName;
    private String lastName;

    private String addressRu;
    private String addressKg;

    private String phoneNumber;

    @OneToMany(mappedBy = "applicant")
    private List<WorkExperience> workExperience;

    @OneToMany(mappedBy = "applicant")
    private List<Education> education;

    @ManyToMany
    @JoinTable(
            name = "applicant_skills",
            joinColumns = @JoinColumn(name = "applicant_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

    @OneToMany(mappedBy = "applicant")
    private List<AwardCertification> awardsAndCertifications;
}
