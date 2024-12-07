package com.studentfeedbacksystem.services;

import com.studentfeedbacksystem.models.*;
import com.studentfeedbacksystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseFeedbackService {
    @Autowired
    private CourseFeedbackRepository feedbackRepository;
    
    @Autowired
    private CourseRepository courseRepository;

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public CourseFeedback submitFeedback(CourseFeedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<CourseFeedback> getFeedbackByCourse(Long courseId) {
        return feedbackRepository.findByCourseId(courseId);
    }

    public FeedbackStats getFeedbackStats(Long courseId) {
        Object[] stats = feedbackRepository.getAverageRatingsByCourseId(courseId);

        FeedbackStats feedbackStats = new FeedbackStats();

        feedbackStats.setAverageTeaching(((Number) stats[0]).doubleValue());
        feedbackStats.setAverageContent(((Number) stats[1]).doubleValue());
        feedbackStats.setAverageMaterials(((Number) stats[2]).doubleValue());
        feedbackStats.setAverageEngagement(((Number) stats[3]).doubleValue());
        feedbackStats.setAverageSatisfaction(((Number) stats[4]).doubleValue());
        feedbackStats.setTotalResponses(((Number) stats[5]).intValue());

        return feedbackStats;
    }

}
