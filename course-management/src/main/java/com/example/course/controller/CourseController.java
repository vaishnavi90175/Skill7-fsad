package com.example.course.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.course.entity.Course;
import com.example.course.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    // CREATE
    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        Course saved = service.addCourse(course);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(service.getAllCourses(), HttpStatus.OK);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Optional<Course> course = service.getCourseById(id);
        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found with id: " + id);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id,
                                          @RequestBody Course course) {

        if (service.getCourseById(id).isPresent()) {
            return ResponseEntity.ok(service.updateCourse(id, course));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found for update");
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {

        if (service.getCourseById(id).isPresent()) {
            service.deleteCourse(id);
            return ResponseEntity.ok("Course deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found for deletion");
        }
    }

    // SEARCH BY TITLE
    @GetMapping("/search/{title}")
    public ResponseEntity<List<Course>> searchByTitle(@PathVariable String title) {
        return ResponseEntity.ok(service.searchByTitle(title));
    }
}