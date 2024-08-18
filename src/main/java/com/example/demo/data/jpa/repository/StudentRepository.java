package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.Course;
import com.example.demo.data.jpa.entity.Passport;
import com.example.demo.data.jpa.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StudentRepository {
    @Autowired
    private EntityManager em;

    public Student findById(Long id){
        return em.find(Student.class, id);
    }

    public Student save(Student student){
        if(student.getId() == null){
            em.persist(student);
        } else {
            em.merge(student);
        }
        return student;
    }

    public void  deleteById(Long id){
        Student student = findById(id);
        em.remove(student);
    }

    public void saveWithPassport(){
        Passport passport = new Passport("PS0993");
        em.persist(passport);
        
        Student student = new Student("student6");
        student.setPassport(passport);
        em.persist(student);
    }

    public void saveWithCourse(Student student, Course course){
        course.addStudent(student);
        student.addCourse(course);
        em.persist(course);
        em.persist(student);
    }

}
