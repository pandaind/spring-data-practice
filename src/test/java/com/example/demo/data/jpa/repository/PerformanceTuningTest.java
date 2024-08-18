package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.Course;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PerformanceTuningTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    void n_plus_one_problem(){
        List<Course> courses = em.createNamedQuery("jpql_get_all_courses", Course.class)
                .getResultList();
        for (Course c : courses) {
            c.getStudents(); // if 1000 courses then 1000 time sql query will execute
        }
    }

    @Test
    void n_plus_one_solution_entity_graph(){
        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        Subgraph<Object> students = entityGraph.addSubgraph("students");

        List<Course> courses = em.createNamedQuery("jpql_get_all_courses", Course.class)
                .setHint("jakarta.persistence.loadgraph", entityGraph)
                .getResultList();
        for (Course c : courses) {
            c.getStudents(); // if 1000 courses then 1000 time sql query will execute
        }
    }

    @Test
    void n_plus_one_solution_join(){
        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        Subgraph<Object> students = entityGraph.addSubgraph("students");

        List<Course> courses = em.createNamedQuery("jpql_get_all_courses_join_fetch", Course.class)
                .setHint("jakarta.persistence.loadgraph", entityGraph)
                .getResultList();
        for (Course c : courses) {
            c.getStudents(); // if 1000 courses then 1000 time sql query will execute
        }
    }
}
