package com.example.demo.repositories;


import com.example.demo.models.*;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest(properties = {
        "spring.test.database.replace=NONE"
})
@ExtendWith(SpringExtension.class)
public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    LearningPlanRepository learningPlanRepository;

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Test
    public void add_course_without_learningPlan_without_content_without_enrollment_without_session()
    {
        Course c1 = new Course();
        c1 = courseRepository.save(c1);

        assertNotNull(c1.getId());
    }

    @Test
    public void delete_course_without_learningPlan_without_content_without_enrollment_without_session()
    {
        Course c1 = new Course();
        c1 = courseRepository.save(c1);
        courseRepository.deleteById(c1.getId());
        Optional<Course> opc1 = courseRepository.findById(c1.getId());
        assertFalse(opc1.isPresent());
    }

    @Test
    public void add_course_with_learningPlan_with_content_with_enrollment_with_session()
    {
        LearningPlan lp1 = new LearningPlan();
        Content co1 = new Content();
        Enrollment cn1 = new Enrollment();
        Session se1 = new Session();
        Course c1 = new Course();

        c1.getLearningPlan().add(lp1);
        c1.getContents().add(co1);
        c1.getEnrollments().add(cn1);
        c1.getSessions().add(se1);
        c1 = courseRepository.save(c1);

        assertNotNull(c1.getId());
    }

    @Test
    public void delete_course_with_learningPlan_with_content_with_enrollment_with_session()
    {
        LearningPlan lp1 = new LearningPlan();
        lp1 = learningPlanRepository.save(lp1);
        Content co1 = new Content();
        co1 = contentRepository.save(co1);
        Enrollment en1 = new Enrollment();
        en1 = enrollmentRepository.save(en1);
        Session se1 = new Session();
        se1 = sessionRepository.save(se1);
        Course c1 = new Course();

        c1.getLearningPlan().add(lp1);
        c1.getContents().add(co1);
        c1.getEnrollments().add(en1);
        c1.getSessions().add(se1);
        c1 = courseRepository.save(c1);
        courseRepository.deleteById(c1.getId());

        Optional<Course> oc1 = courseRepository.findById(c1.getId());
        Optional<LearningPlan> olp1 = learningPlanRepository.findById(lp1.getId());
        Optional<Content> oco1 = contentRepository.findById(co1.getId());
        Optional<Enrollment> oen1 = enrollmentRepository.findById(en1.getId());
        Optional<Session> ose1 = sessionRepository.findById(se1.getId());

        assertFalse(oc1.isPresent());
        assertTrue(olp1.isPresent());
        assertTrue(oco1.isPresent());
        assertTrue(oen1.isPresent());
        assertTrue(ose1.isPresent());
    }

    //TODO: add testcases for saving multiple course with overlapping learningPlans, contents, enrollments and sessions

}