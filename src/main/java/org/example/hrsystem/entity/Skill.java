package org.example.hrsystem.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.hrsystem.entity.base.BaseEntity;
import org.example.hrsystem.entity.base.SoftDeletable;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Audited
@Where(clause = "deleted_at IS NULL")
public class Skill extends SoftDeletable {

    private String skillName;
}
