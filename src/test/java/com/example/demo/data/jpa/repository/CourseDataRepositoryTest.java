package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CourseDataRepositoryTest {

    @Autowired
    private CourseDataRepository dataRepository;

    @Test
    void findById() {
        Optional<Course> courseOptional = dataRepository.findById(10001L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    void findByName() {
        List<Course> courses = dataRepository.findByName("Spring Batch");
        assertTrue(courses.size() > 0);
    }

    @Test
    void sort() {
        Sort sort = Sort.by(Sort.Order.desc("name"));
        sort.and(Sort.Order.desc("lastUpdatedDate"));
        dataRepository.findAll(sort);
    }

    @Test
    void paginate() {
        PageRequest pageRequest = PageRequest.of(0, 2);

        Page<Course> coursePage = dataRepository.findAll(pageRequest);
        List<Course> courses = coursePage.getContent();

        assertEquals(2, courses.size());
    }
}