package com.example.demo.models;

import com.example.demo.models.generic.AuditableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SESSIONS")
public class Session extends AuditableEntity {

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "PLATFORM_ID")
    private LearningPlatform learningPlatform;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "COURSE_ID", nullable = false)
    private Course course;
}
