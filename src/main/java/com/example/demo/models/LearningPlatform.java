package com.example.demo.models;

import com.example.demo.models.generic.AuditableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LEARNING_PLATFORM")
public class LearningPlatform extends AuditableEntity {

    @Getter
    @Setter
    private String name;

}
