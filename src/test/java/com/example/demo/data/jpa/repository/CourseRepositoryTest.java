package com.example.demo.data.jpa.repository;

import com.example.demo.data.SpringDataJpaPracticeApplication;
import com.example.demo.data.jpa.entity.Course;
import com.example.demo.data.jpa.entity.Review;
import com.example.demo.data.jpa.entity.ReviewRating;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = SpringDataJpaPracticeApplication.class)
class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    @DirtiesContext // reset the data after test back to normal
    void findById_basic() {
        Course course = repository.findById(10001L);
        assertEquals("Spring Data JPA", course.getName());
    }

    @Test
    @DirtiesContext // reset the data after test back to normal
    void save_basic() {
        //get a course
        Course course = repository.findById(10001L);
        assertEquals("Spring Data JPA", course.getName());

        //update details
        course.setName("Spring Data Cassandra");
        repository.save(course);

        //check the value
        Course course1 = repository.findById(10001L);
        assertEquals("Spring Data Cassandra", course1.getName());
    }

    @Test
    @DirtiesContext // reset the data after test back to normal
    void deleteById_basic() {
        repository.deleteById(10002L);
        assertNull(repository.findById(10002L));
    }

    @Test
    @DirtiesContext
    void entityManager_experiments_test(){
        repository.entityManager_experiments();
    }

    @Test
    @DirtiesContext
    @Transactional
    void addReview_for_course_test(){
        Course course = repository.findById(10001L);
        List<Review> reviews = course.getReviews();

        Review review1 = new Review(ReviewRating.FIVE, "Very nice course");
        Review review2 = new Review(ReviewRating.THREE, "Nice course but very basic");

        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        em.persist(review1);
        em.persist(review2);
    }

    @Test
    @DirtiesContext
    void addReviewForCourseTest(){
        List<Review> reviews = new ArrayList<>();
        Review review1 = new Review(ReviewRating.FIVE, "Very nice course");
        Review review2 = new Review(ReviewRating.THREE, "Nice course but very basic");

        reviews.add(review1);
        reviews.add(review2);

        repository.addReviewForCourse(10001L, reviews);
    }

    @Transactional
    void firstLevelCacheDemo(){
        // will print only 1 query in one transactional
        // uses first level cache of persistence context
        // if @Transactional is removed then it will print query
        // for each findById call
        repository.findById(1001l);
        repository.findById(1001l);
        repository.findById(1001l);
        repository.findById(1001l);
    }
}