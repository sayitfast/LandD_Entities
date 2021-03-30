package com.example.demo.repositories;

import com.example.demo.models.*;
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
public class EnrollmentRepositoryTest {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    LearningPlanRepository learningPlanRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    LearningPlatformRepository learningPlatformRepository;

    @Test
    public void add_enrollment_without_learningPlan_without_platform_without_enrollment_without_session()
    {
        Enrollment en1 = new Enrollment();
        en1 = enrollmentRepository.save(en1);

        assertNotNull(en1.getId());
    }


    @Test
    public void delete_enrollment_without_learningPlan_without_platform_without_course_without_session()
    {
        Enrollment en1 = new Enrollment();
        en1 = enrollmentRepository.save(en1);
        enrollmentRepository.deleteById(en1.getId());
        Optional<Enrollment> oen1 = enrollmentRepository.findById(en1.getId());
        assertFalse(oen1.isPresent());
    }

    @Test
    public void add_enrollment_with_learningPlan_with_platform_with_course_with_session()
    {
        LearningPlan lp1 = new LearningPlan();
        LearningPlatform lpl1 = new LearningPlatform();
        Course co1 = new Course();
        Session se1 = new Session();
        Enrollment en1 = new Enrollment();

        en1.setLearningPlan(lp1);
        en1.setPlatform(lpl1);
        en1.setCourse(co1);
        en1.setSession(se1);
        en1 = enrollmentRepository.save(en1);

        assertNotNull(en1.getId());
    }

    @Test
    public void delete_enrollment_with_learningPlan_with_platform_with_course_with_session()
    {
        LearningPlan lp1 = new LearningPlan();
        lp1 = learningPlanRepository.save(lp1);
        LearningPlatform lpl1 = new LearningPlatform();
        lpl1 = learningPlatformRepository.save(lpl1);
        Course co1 = new Course();
        co1 = courseRepository.save(co1);
        Session se1 = new Session();
        se1 = sessionRepository.save(se1);
        Enrollment en1 = new Enrollment();

        en1.setLearningPlan(lp1);
        en1.setPlatform(lpl1);
        en1.setCourse(co1);
        en1.setSession(se1);
        en1 = enrollmentRepository.save(en1);
        enrollmentRepository.deleteById(en1.getId());

        Optional<Enrollment> oc1 = enrollmentRepository.findById(en1.getId());
        Optional<LearningPlan> olp1 = learningPlanRepository.findById(lp1.getId());
        Optional<LearningPlatform> olpl1 = learningPlatformRepository.findById(lpl1.getId());
        Optional<Course> oco1 = courseRepository.findById(co1.getId());
        Optional<Session> ose1 = sessionRepository.findById(se1.getId());

        assertFalse(oc1.isPresent());
        assertTrue(olp1.isPresent());
        assertTrue(olpl1.isPresent());
        assertTrue(oco1.isPresent());
        assertTrue(ose1.isPresent());
    }
}