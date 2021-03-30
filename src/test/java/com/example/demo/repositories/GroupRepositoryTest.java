package com.example.demo.repositories;

import com.example.demo.models.Group;
import com.example.demo.models.Learner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@DataJpaTest(properties = {
        "spring.test.database.replace=NONE"
})
@ExtendWith(SpringExtension.class)
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private LearnerRepository learnerRepository;


    @Test
    public void add_group_without_members()
    {
        Group group1 = new Group();
        group1.setName("TEST GROUP 1");
        group1 = groupRepository.save(group1);
        assertNotNull(group1.getId());
    }

    @Test
    public void delete_group_without_members()
    {
        Group group1 = new Group();
        group1.setName("TEST GROUP 1");
        group1 = groupRepository.save(group1);
        groupRepository.deleteById(group1.getId());
        Optional<Group> result = groupRepository.findById(group1.getId());
        assertFalse(result.isPresent());
    }

    @Test
    public void retrieve_group_without_members()
    {
        Group group1 = new Group();
        group1.setName("TEST GROUP 1");
        group1 = groupRepository.save(group1);
        Optional<Group> result = groupRepository.findById(group1.getId());
        assertTrue(result.isPresent());
    }

    @Test
    public void delete_group_containing_members()
    {
        Learner l1 = new Learner();
        Learner l2 = new Learner();
        l1 = learnerRepository.save(l1);
        l2 = learnerRepository.save(l2);

        Group group1 = new Group();
        group1.setName("TEST GROUP 1");
        group1.getLearners().add(l1);
        group1.getLearners().add(l2);
        group1 = groupRepository.save(group1);
        groupRepository.deleteById(group1.getId());

        Optional<Group> result = groupRepository.findById(group1.getId());
        Optional<Learner> opl1 = learnerRepository.findById(l1.getId());
        Optional<Learner> opl2 = learnerRepository.findById(l2.getId());

        assertTrue(opl1.isPresent());
        assertTrue(opl2.isPresent());
        assertFalse(result.isPresent());
    }

    @Test
    public void retrieve_group_containing_members()
    {
        Learner l1 = new Learner();
        Learner l2 = new Learner();
        l1 = learnerRepository.save(l1);
        l2 = learnerRepository.save(l2);

        Group group1 = new Group();
        group1.setName("TEST GROUP 1");
        group1.getLearners().add(l1);
        group1.getLearners().add(l2);
        group1 = groupRepository.save(group1);
        Optional<Group> result = groupRepository.findById(group1.getId());
        assertTrue(result.isPresent());
    }
}