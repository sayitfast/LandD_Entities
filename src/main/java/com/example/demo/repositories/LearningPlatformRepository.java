package com.example.demo.repositories;

import com.example.demo.models.LearningPlatform;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningPlatformRepository extends CrudRepository<LearningPlatform, Long> {
}
