package com.example.demo.data.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating", nullable = false)
    private ReviewRating rating;

    @Column(name = "description")
    private String description;

    @ManyToOne // default is eager fetching
    private Course course;

    public Review() {
    }

    public Review(ReviewRating rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReviewRating getRating() {
        return rating;
    }

    public void setRating(ReviewRating rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}
