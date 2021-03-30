package com.example.demo.models;

import com.example.demo.models.generic.AuditableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "LEARNING_PLAN")
public class LearningPlan extends AuditableEntity {

    @ManyToOne
    private LearningPlatform platformId;

    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "LEARNING_PLAN_COURSE",
            joinColumns = {@JoinColumn(name = "PLAN_ID")},
            inverseJoinColumns = {@JoinColumn(name = "COURSE_ID")}
    )
    private Set<Course> courses;

    public LearningPlan()
    {
        this.courses = new HashSet<>();
    }
}
