package com.studentfeedbacksystem.controllers;

import com.studentfeedbacksystem.models.FeedbackForm;
import com.studentfeedbacksystem.models.FeedbackSubmission;
import com.studentfeedbacksystem.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/admin/feedback/forms")
    public List<FeedbackForm> getAllForms() {
        return feedbackService.getAllForms();
    }

    @PostMapping("/admin/feedback/forms")
    public FeedbackForm createForm(@RequestBody FeedbackForm form) {
        return feedbackService.createForm(form);
    }

    @DeleteMapping("/admin/feedback/forms/{formId}")
    public void deleteForm(@PathVariable int formId) {
        feedbackService.deleteForm(formId);
    }

    @GetMapping("/student/feedback/forms")
    public List<FeedbackForm> getStudentForms() {
        return feedbackService.getAllForms();
    }

    @PostMapping("/student/feedback/{formId}")
    public FeedbackSubmission submitFeedback(@PathVariable int formId, @RequestBody FeedbackSubmission submission) {
        return feedbackService.submitFeedback(formId, submission);
    }
}
