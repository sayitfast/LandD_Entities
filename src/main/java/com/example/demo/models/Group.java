package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.models.generic.AuditableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "groups")
public class Group extends AuditableEntity {

    @Getter
    @Setter
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinColumn(name= "platform_id")
    private LearningPlatform platform;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "LearnerGroupAssignments",
        joinColumns = {@JoinColumn(name = "group_id")},
        inverseJoinColumns = {@JoinColumn(name = "learner_id")}
    )
    private Set<Learner> learners;

    public Group()
    {
        learners = new HashSet<>();
    }

}
