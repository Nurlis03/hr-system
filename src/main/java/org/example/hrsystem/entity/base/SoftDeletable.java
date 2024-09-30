package org.example.hrsystem.entity.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.hrsystem.entity.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class SoftDeletable extends Auditable {

    private LocalDateTime deletedAt;

    @OneToOne
    @JoinColumn(name = "deleted_by")
    private User deletedBy;
}
