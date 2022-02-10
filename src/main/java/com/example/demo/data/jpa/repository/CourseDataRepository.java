package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "courses")
public interface CourseDataRepository extends JpaRepository<Course, Long> {
    List<Course> findByName(String name);

    List<Course> findByNameOrderByIdDesc(String name);

    void deleteByName(String name);

    @Query("Select c from Course c where name like '%Spring%'")
    List<Course> queryLikeSpring();

    @Query(value = "SELECT * FROM COURSE", nativeQuery = true)
    List<Course> queryLikeSpringNative(String name);

    @Query(name = "jpql_get_all_courses")
    List<Course> queryNamed();

}
