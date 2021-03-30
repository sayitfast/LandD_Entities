package com.example.demo.models;

import com.example.demo.models.generic.AuditableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COURSES")
public class Course extends AuditableEntity {

    @Getter
    @Setter
    @ManyToMany(mappedBy = "courses",
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    private Set<LearningPlan> learningPlan;

    @Getter
    @Setter
    @OneToMany(mappedBy = "course",
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    private Set<Content> contents;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    private LearningPlatform platform;

    @Getter
    @Setter
    @OneToMany(mappedBy = "course",
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    private Set<Enrollment> enrollments;

    @Getter
    @Setter
    @OneToMany(mappedBy = "course",
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    private Set<Session> sessions;

    public Course()
    {
        this.learningPlan = new HashSet<>();
        this.contents = new HashSet<>();
        this.enrollments = new HashSet<>();
        this.sessions = new HashSet<>();
    }
}
