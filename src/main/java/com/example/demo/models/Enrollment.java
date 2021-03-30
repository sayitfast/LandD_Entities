package com.example.demo.models;

import com.example.demo.models.generic.AuditableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ENTROLLMENTS")
public class Enrollment extends AuditableEntity {

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID", nullable = false)
    private Course course;

    @Getter
    @Setter
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "SESSION_ID")
    private Session session;

    @Getter
    @Setter
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "LEARNING_PLAN_ID")
    private LearningPlan learningPlan;

    @Getter
    @Setter
    @Column(name = "ENROLLMENT_DATE")
    private LocalDateTime enrollmentDate;

    @Getter
    @Setter
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "PLATFORM_ID")
    private LearningPlatform platform;
}
