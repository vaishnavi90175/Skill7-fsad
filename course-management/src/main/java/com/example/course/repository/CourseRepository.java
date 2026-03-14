package com.example.course.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.course.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTitleContainingIgnoreCase(String title);
}