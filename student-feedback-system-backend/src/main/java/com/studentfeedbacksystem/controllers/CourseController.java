package com.studentfeedbacksystem.controllers;

import com.studentfeedbacksystem.models.*;
import com.studentfeedbacksystem.services.CourseFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {
    
    @Autowired
    private CourseFeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        return ResponseEntity.ok(feedbackService.addCourse(course));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(feedbackService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = feedbackService.getCourseById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{courseId}/feedback")
    public ResponseEntity<CourseFeedback> submitFeedback(
            @PathVariable Long courseId,
            @RequestBody CourseFeedback feedback) {
        feedback.setCourseId(courseId);
        return ResponseEntity.ok(feedbackService.submitFeedback(feedback));
    }

    @GetMapping("/{courseId}/feedback")
    public ResponseEntity<List<CourseFeedback>> getFeedbackByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(feedbackService.getFeedbackByCourse(courseId));
    }

    @GetMapping("/{courseId}/stats")
    public ResponseEntity<FeedbackStats> getFeedbackStats(@PathVariable Long courseId) {
        return ResponseEntity.ok(feedbackService.getFeedbackStats(courseId));
    }
}