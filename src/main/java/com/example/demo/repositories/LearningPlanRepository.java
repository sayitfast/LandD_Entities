package com.example.demo.repositories;

import com.example.demo.models.LearningPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningPlanRepository extends CrudRepository<LearningPlan, Long> {
}
