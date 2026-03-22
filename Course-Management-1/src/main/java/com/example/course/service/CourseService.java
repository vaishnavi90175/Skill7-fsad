package com.example.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.entity.Course;
import com.example.course.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repo;

    public Course addCourse(Course course) {
        return repo.save(course);
    }

    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return repo.findById(id);
    }

    public Course updateCourse(Long id, Course newCourse) {
        return repo.findById(id).map(course -> {
            course.setTitle(newCourse.getTitle());
            course.setDuration(newCourse.getDuration());
            course.setFee(newCourse.getFee());
            return repo.save(course);
        }).orElse(null);
    }

    public boolean deleteCourse(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Course> searchByTitle(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }
}