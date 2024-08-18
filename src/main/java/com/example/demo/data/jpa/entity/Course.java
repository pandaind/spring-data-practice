package com.example.demo.data.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Course")
@NamedQueries(value = {
        @NamedQuery(name = "jpql_get_all_courses", query = "from Course"),
        @NamedQuery(name = "jpql_get_all_courses_join_fetch", query = "select c from Course c JOIN FETCH c.students s"),
        @NamedQuery(name = "jpql_get_all_spring_courses", query="select c from Course c where name like '%Spring%'")
})
@Cacheable
@SQLDelete(sql = "update course set is_deleted=true where id=?") // soft delete .... you need to handle in native query
@Where(clause = "is_deleted = false") // don't retrieve soft deleted records .... you need to handle in native query
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @UpdateTimestamp // hibernate specific annotation
    private LocalDateTime createdDate;

    @CreationTimestamp // hibernate specific annotation
    private LocalDateTime lastUpdatedDate;

    @OneToMany(mappedBy = "course") // default lazy fetching
    private List<Review> reviews =  new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL ,mappedBy="courses")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    private boolean isDeleted;

    public Course(){}

    public Course(String name) {
        this.name = name;
    }

    @PreRemove // called before removing the entity... this is to handle soft delete in a transaction
                // on persistent context
    private void preRemove() {
        this.isDeleted = true;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review){
        this.reviews.add(review);
    }

    public void removeReview(Review review){
        this.reviews.remove(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    /**
     *  Do not put associated mapped attributes.. otherwise you will pull those
     *  data from db even if lazy loading is enabled
     * @return
     */
    @Override
    public String toString() {
        return "\nCourse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
