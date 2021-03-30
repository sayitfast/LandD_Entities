package com.example.demo.repositories;

import com.example.demo.models.LearningPlatform;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest(properties = {
        "spring.test.database.replace=NONE"
})
public class LearningPlatformRepositoryTest {

    @Autowired
    LearningPlatformRepository learningPlatformRepository;

    @Test
    public void add_learningPlatform()
    {
        LearningPlatform lp1 = new LearningPlatform();
        lp1 = learningPlatformRepository.save(lp1);

        assertNotNull(lp1.getId());
    }

    @Test
    public void delete_learningPlatform()
    {
        LearningPlatform lp1 = new LearningPlatform();
        lp1 = learningPlatformRepository.save(lp1);
        learningPlatformRepository.deleteById(lp1.getId());
        Optional<LearningPlatform> olp1 = learningPlatformRepository.findById(lp1.getId());

        assertFalse(olp1.isPresent());
    }
}