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
public class Company extends SoftDeletable {
    private String companyTitleRu;
    private String companyTitleKg;

    private String addressRu;
    private String addressKg;

    private Long foundedYear;
    private String phoneNumber;
    private String email;
    private String website;
    private byte[] logo;
}
