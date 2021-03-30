package com.example.demo.repositories;

import java.util.Set;

import com.example.demo.models.Learner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerRepository extends CrudRepository<Learner, Long> {

    Set<Learner> findAllByGroups_Id(long id);
}
