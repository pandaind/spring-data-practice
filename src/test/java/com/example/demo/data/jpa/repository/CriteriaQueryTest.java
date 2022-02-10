package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@SpringBootTest
public class CriteriaQueryTest {
    @Autowired
    private EntityManager em;

    @Test
    void criteria_query_basic(){
        /**
         * 1. use CriteriaBuilder  to create  a Criteria Query returning  the expected result object
         * 2. define roots for tables which involved the query
         * 3. define predicate etc using Criteria Builder
         * 4. add predicates etc to Criteria query
         * 5. build the TypedQuery using the entity manager and criteria query
         */
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        List<Course> results = em.createQuery(cq.select(courseRoot)).getResultList();
    }

    @Test
    void criteria_query_where(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        Predicate likeSpring = cb.like(courseRoot.get("name"), "%Spring%");

        cq.where(likeSpring);

        List<Course> results = em.createQuery(cq.select(courseRoot)).getResultList();
    }

    @Test
    void criteria_query_courses_without_student(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));

        cq.where(studentsIsEmpty);

        List<Course> results = em.createQuery(cq.select(courseRoot)).getResultList();
    }

    @Test
    void criteria_query_course_join_student(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        Join<Object, Object> join = courseRoot.join("students");

        List<Course> results = em.createQuery(cq.select(courseRoot)).getResultList();
    }

    @Test
    void criteria_query_course_left_join_student(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

        List<Course> results = em.createQuery(cq.select(courseRoot)).getResultList();
    }
}
