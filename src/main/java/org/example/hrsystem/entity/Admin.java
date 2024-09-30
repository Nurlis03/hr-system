package org.example.hrsystem.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Where(clause = "deleted_at IS NULL")
public class Admin extends User {
    private String firstName;
    private String lastName;
}
