package com.example.demo.models;

import com.example.demo.models.generic.AuditableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "learners")
public class Learner extends AuditableEntity {

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "platform_id")
    private LearningPlatform platform;

    @Getter
    @Setter
    private Long platformUserId;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "learners",
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY
            )
    private Set<Group> groups;

    public Learner()
    {
        this.groups = new HashSet<>();
    }

    public void removeGroup(Group group)
    {
        this.groups.remove(group);
        group.getLearners().remove(this);
    }

    public void addGroup(Group group)
    {
        this.groups.add(group);
        group.getLearners().add(this);
    }
}
