package com.example.demo.repositories;

import com.example.demo.models.Course;
import com.example.demo.models.LearningPlatform;
import com.example.demo.models.Session;
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
public class SessionRepositoryTest {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    LearningPlatformRepository learningPlatformRepository;

    @Test
    public void add_session_without_course_without_learningPlatform()
    {
        Session se1 = new Session();
        se1 = sessionRepository.save(se1);

        assertNotNull(se1.getId());
    }

    @Test
    public void add_session_with_course_with_learningPlatform()
    {
        Session se1 = new Session();
        Course co1 = new Course();
        LearningPlatform lp1 = new LearningPlatform();
        se1.setCourse(co1);
        se1.setLearningPlatform(lp1);

        se1 = sessionRepository.save(se1);
        Optional<Course> oco1 = courseRepository.findById(co1.getId());
        Optional<LearningPlatform> olp1 = learningPlatformRepository.findById(lp1.getId());

        assertNotNull(se1.getId());
        assertTrue(oco1.isPresent());
        assertTrue(olp1.isPresent());
    }

    @Test
    public void delete_session_without_course_without_learningPlatform()
    {
        Session se1 = new Session();
        se1 = sessionRepository.save(se1);
        sessionRepository.deleteById(se1.getId());
        Optional<Session> ose1 = sessionRepository.findById(se1.getId());
        assertFalse(ose1.isPresent());
    }

    @Test
    public void delete_session_with_course_with_learningPlatform()
    {
        Course co1 = new Course();
        co1 = courseRepository.save(co1);
        LearningPlatform lp1 = new LearningPlatform();
        lp1 = learningPlatformRepository.save(lp1);

        Session se1 = new Session();
        se1.setCourse(co1);
        se1.setLearningPlatform(lp1);

        se1 = sessionRepository.save(se1);
        sessionRepository.deleteById(se1.getId());
        Optional<Session> ose1 = sessionRepository.findById(se1.getId());
        Optional<Course> oco1 = courseRepository.findById(co1.getId());
        Optional<LearningPlatform> olp1 = learningPlatformRepository.findById(lp1.getId());

        assertFalse(ose1.isPresent());
        assertTrue(oco1.isPresent());
        assertTrue(olp1.isPresent());
    }
}