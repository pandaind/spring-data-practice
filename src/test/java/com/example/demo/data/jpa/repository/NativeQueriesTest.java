package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class NativeQueriesTest {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    @DirtiesContext
        // reset the data after test back to normal
    void native_queries_basic() {
        List<Course> result = em.createNativeQuery("SELECT * FROM COURSE where is_deleted=false", Course.class).getResultList();
        assertTrue(result.stream().map(c -> c.getName()).collect(Collectors.toList()).contains("Spring Data JPA"));
    }

    @Test
    @DirtiesContext // reset the data after test back to normal
    void native_queries_where() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id = ? and is_deleted=?", Course.class);
        query.setParameter(1, 10001L);
        query.setParameter(2, false);
        List<Course> result = query.getResultList();
        assertTrue(result.stream().anyMatch(c -> c.getName().contains("Spring")));
    }

    @Test
    @DirtiesContext // reset the data after test back to normal
    void native_queries_where_named_parameter() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id = :id and is_deleted=:isDeleted", Course.class);
        query.setParameter("id", 10001L);
        query.setParameter("isDeleted", false);
        List<Course> result = query.getResultList();
        assertTrue(result.stream().anyMatch(c -> c.getName().contains("Spring")));
    }

    /**
     * 1. Use Native queries for mass update and mass clean up
     * 2. While using native queries don't use persistence context or refresh the persistence context before execute
     */
    @Test
    @Transactional
    @DirtiesContext // reset the data after test back to normal
    void native_queries_to_mass_update() {
        Query query = em.createNativeQuery("UPDATE COURSE SET last_updated_date=CURRENT_TIMESTAMP", Course.class);
        int noOfRows = query.executeUpdate();
        assertTrue(noOfRows > 0);
    }

}
