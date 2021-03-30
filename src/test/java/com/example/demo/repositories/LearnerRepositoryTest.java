package com.example.demo.repositories;

import com.example.demo.models.Group;
import com.example.demo.models.Learner;
import com.example.demo.models.LearningPlatform;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest(properties = {
        "spring.test.database.replace=NONE"
})
@ExtendWith(SpringExtension.class)
public class LearnerRepositoryTest {

    @Autowired
    LearnerRepository learnerRepository;
    @Autowired
    LearningPlatformRepository learningPlatformRepository;
    @Autowired
    GroupRepository groupRepository;

    @Test
    public void add_learner_with_platform()
    {
        LearningPlatform lp1 = new LearningPlatform();
        lp1.setName("TEST PLATFORM 1");
        lp1 = learningPlatformRepository.save(lp1);

        Learner l1 = new Learner();
        l1.setPlatform(lp1);
        l1 = learnerRepository.save(l1);

        assertNotNull(l1.getId());
    }

    @Test
    public void add_learner_without_platform()
    {
        Learner l1 = new Learner();
        l1 = learnerRepository.save(l1);

        assertNotNull(l1.getId());
    }

    @Test
    public void add_learner_with_group()
    {
        Group gr1 = new Group();
        gr1.setName("TEST GROUP 1");
        gr1 = groupRepository.save(gr1);

        Learner l1 = new Learner();
        l1.addGroup(gr1);
        l1 = learnerRepository.save(l1);

        assertNotNull(l1.getId());
    }

    @Test
    public void add_learner_without_group()
    {
        Learner l1 = new Learner();
        l1 = learnerRepository.save(l1);

        assertNotNull(l1.getId());
    }
}