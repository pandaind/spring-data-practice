package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.Course;
import com.example.demo.data.jpa.entity.Review;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CourseRepository {
    @Autowired
    private EntityManager em;

    public Course findById(Long id){
        return em.find(Course.class, id);
    }

    public Course save(Course course){
        if(course.getId() == null){
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    public void  deleteById(Long id){
        Course course = findById(id);
        em.remove(course);
    }

    public void entityManager_experiments(){
        Course course = new Course("Web Services");
        Course course1 = new Course("Hibernates");
        em.persist(course);
        em.persist(course1);

        course.setName("Web Services - updated"); // this data updated to the db as its single transaction
        course.setName("Java Core");

        em.flush();
        em.refresh(course); // course will be refreshed to content comes back from db
        course.setName("Web Service - New");

        em.flush(); // flush the data to db

        em.detach(course1); // detach the object, below codes will not persist even on flush
        course1.setName("Hibernates - updated");
        em.flush();
        em.clear(); // Detach everything from em
        course.setName("Web Services - latest");
    }

    public void addReviewForCourse(Long courseId, List<Review> reviews){
        Course course = findById(courseId);

        for(Review review : reviews){
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }
    }
}
