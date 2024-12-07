package com.studentfeedbacksystem.controllers;

import com.studentfeedbacksystem.models.Faculty;
import com.studentfeedbacksystem.models.FacultyFeedback;
import com.studentfeedbacksystem.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @PostMapping("/create")
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.createFaculty(faculty));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @PostMapping("/feedback")
    public ResponseEntity<?> submitFeedback(@RequestBody FacultyFeedback feedback) {
        try {
            return ResponseEntity.ok(facultyService.submitFeedback(feedback));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping("/analytics/{facultyId}")
    public ResponseEntity<Map<String, Object>> getFacultyAnalytics(@PathVariable Long facultyId) {
        return ResponseEntity.ok(facultyService.getFacultyAnalytics(facultyId));
    }
}