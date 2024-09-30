package org.example.hrsystem.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.hrsystem.entity.base.BaseEntity;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Where(clause = "deleted_at IS NULL")
public class HrSpecialist extends User {

    private String firstName;

    private String lastName;

    @OneToOne
    private Company company;

    @OneToMany
    private List<Vacancy> vacancies;
}
