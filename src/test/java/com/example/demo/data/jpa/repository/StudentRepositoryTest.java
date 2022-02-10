package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.Course;
import com.example.demo.data.jpa.entity.Passport;
import com.example.demo.data.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void fetch_student_and_passport_test() {
        Student student = em.find(Student.class, 10001L);
        Passport passport = student.getPassport();
        assertEquals("PS001", passport.getNumber());
    }

    @Test
    @Transactional
    void fetch_passport_and_student_test() {
        Passport passport = em.find(Passport.class, 10001L);
        Student student = passport.getStudent();
        assertEquals("student1", student.getName());
    }

    @Test
    @Transactional
        // if removed then each operation is separate transaction
    void transactional_test() {
        // Retrieve Student
        Student student = em.find(Student.class, 10001L);
        // Retrieve Passport
        Passport passport = student.getPassport();
        // Update Passport
        passport.setNumber("PS002");
        // Update Student
        student.setName("student1 updated");
    }

    @Test
    @Transactional
    void fetch_student_and_courses_test() {
        Student student = em.find(Student.class, 10001L);
        List<Course> courses = student.getCourses();
        assertTrue(courses.size() > 0);
    }

    @Test
    @Transactional
    @DirtiesContext
    void insert_student_and_course(){
        Student student = new Student("student-x");
        Course course = new Course("course-x");
        student.addCourse(course);
        course.addStudent(student);

        em.persist(course);
        em.persist(student);
    }

    @Test
    @Transactional
    @DirtiesContext
    void saveWithCourse_test() {
        Student student = new Student("student-x");
        Course course = new Course("course-x");
        repository.saveWithCourse(student, course);
    }
}