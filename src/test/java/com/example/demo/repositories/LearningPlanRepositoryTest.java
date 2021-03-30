package com.example.demo.repositories;

import com.example.demo.models.Course;
import com.example.demo.models.LearningPlan;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest(properties = {
        "spring.test.database.replace=NONE"
})
@ExtendWith(SpringExtension.class)
public class LearningPlanRepositoryTest {

    @Autowired
    LearningPlanRepository learningPlanRepository;

    @Test
    public void add_learningPlan_without_courses()
    {
        LearningPlan lp1 = new LearningPlan();
        lp1 = learningPlanRepository.save(lp1);

        assertNotNull(lp1.getId());
    }

    @Test
    public void add_learningPlan_with_courses()
    {
        Course c1 = new Course();
        Course c2 = new Course();

        LearningPlan lp1 = new LearningPlan();
        lp1.getCourses().addAll(Arrays.asList(c1,c2));
        lp1 = learningPlanRepository.save(lp1);

        assertNotNull(lp1.getId());
    }
}