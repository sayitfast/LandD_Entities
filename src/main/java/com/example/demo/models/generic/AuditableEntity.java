package com.example.demo.models.generic;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class AuditableEntity extends Entity {

    @CreationTimestamp
    @Getter
    @Setter
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Getter
    @Setter
    private LocalDateTime updateDateTime;

}