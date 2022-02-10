package com.example.demo.data;

import com.example.demo.data.jpa.entity.Course;
import com.example.demo.data.jpa.entity.Person;
import com.example.demo.data.jpa.repository.CourseRepository;
import com.example.demo.data.jpa.repository.PersonRepository;
import com.example.demo.data.jpa.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringDataJpaPracticeApplication implements CommandLineRunner {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaPracticeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Person
        // personCRUD();

        // Course
        //courseCRUD();

        //Student
        //studentCRUD();

    }

    private void courseCRUD() {
        Course course = courseRepository.findById(10001L);
        log.info("Course 10001 -> {}", course);
        log.info("Delete 10004 -> ");
        courseRepository.deleteById(10004L);
        log.info("Insert 'Apache Camel' -> ");
        courseRepository.save(new Course("Apache Camel"));
    }

    private void personCRUD() {
        log.info("All Persons -> {}", personRepository.findAll());
        log.info("Person 100001 -> {}", personRepository.findById(100001));
        log.info("Delete 100002 -> ");
        personRepository.deleteById(100002);
        log.info("Insert person4 -> {}", personRepository.insert(new Person("person4", "new york", new Date())));
        log.info("update person3 -> {}", personRepository.update(new Person( 100003, "person3.1", "london", new Date())));
    }

    private void studentCRUD(){
        studentRepository.saveWithPassport();
    }
}
