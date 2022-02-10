package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.Course;
import com.example.demo.data.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class JPQLTest {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    @DirtiesContext
        // reset the data after test back to normal
    void jpql_basic() {
        List<Course> result = em.createNamedQuery("jpql_get_all_courses", Course.class).getResultList();
        assertTrue(result.stream().map(c -> c.getName()).collect(Collectors.toList()).contains("Spring Data JPA"));
    }

    @Test
    @DirtiesContext // reset the data after test back to normal
    void jpql_where() {
        List<Course> result = em.createNamedQuery("jpql_get_all_spring_courses", Course.class).getResultList();
        //log.info(result.toString());
        assertTrue(result.stream().allMatch(c -> c.getName().contains("Spring")));
    }

    @Test
    void jpql_get_course_without_student() {
        List<Course> courses = em.createQuery("Select c from Course c where c.students is empty", Course.class)
                .getResultList();

    }

    @Test
    void jpql_get_course_with_at_least_2_student() {
        List<Course> courses = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class)
                .getResultList();

    }

    @Test
    void jpql_get_course_with_order_by_student() {
        List<Course> courses = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class)
                .getResultList();

    }

    @Test
    void jpql_get_student_with_passport_like() {
        List<Student> students = em.createQuery("Select s from Student s where s.passport.number like '%PS%' ", Student.class)
                .getResultList();

    }
    //LIKE
    //BETWEEN 10 AND 100
    //IS NULL
    //upper, lower, trim, length


    //JOIN -> select c, s from Course c JOIN c.students s
    //LEFT JOIN -> select c, s from Course c LEFT JOIN c.students s
    //CROSS JOIN -> select c, s from Course c , Student s
    @Test
    void jpql_join_test(){
        Query query = em.createQuery("select c, s from Course c JOIN c.students s");
        //Query query = em.createQuery("select c, s from Course c LEFT JOIN c.students s");
        //Query query = em.createQuery("select c, s from Course c , Student s");
        List<Object[]> results = query.getResultList();
        log.info("Size {}", results.size());
        for (Object[] result : results){
            log.info("Course {}, Student {}", result[0], result[1]);
        }
    }
}
