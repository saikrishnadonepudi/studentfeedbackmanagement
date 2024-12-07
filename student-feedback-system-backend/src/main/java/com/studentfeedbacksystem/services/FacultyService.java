package com.studentfeedbacksystem.services;

import com.studentfeedbacksystem.models.Faculty;
import com.studentfeedbacksystem.models.FacultyFeedback;
import com.studentfeedbacksystem.models.User;
import com.studentfeedbacksystem.repositories.FacultyRepository;
import com.studentfeedbacksystem.repositories.FacultyFeedbackRepository;
import com.studentfeedbacksystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private FacultyFeedbackRepository facultyFeedbackRepository;

    @Autowired
    private UserRepository userRepository; // Add this

    @Transactional
    public Faculty createFaculty(Faculty faculty) {
        faculty.setJoinedDate(LocalDateTime.now());
        return facultyRepository.save(faculty);
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    @Transactional
    public FacultyFeedback submitFeedback(FacultyFeedback feedback) {
        Faculty faculty = facultyRepository.findById(feedback.getFaculty().getId())
            .orElseThrow(() -> new RuntimeException("Faculty not found"));

        User student = userRepository.findById(feedback.getStudent().getId())
            .orElseThrow(() -> new RuntimeException("Student not found"));

        feedback.setFaculty(faculty);
        feedback.setStudent(student);
        if (feedback.getSubmissionDate() == null) {
            feedback.setSubmissionDate(LocalDateTime.now());
        }
        if (feedback.getCategory() == null) {
            feedback.setCategory(FacultyFeedback.FeedbackCategory.AVERAGE); // Example default
        }

        return facultyFeedbackRepository.save(feedback);
    }



    public Map<String, Object> getFacultyAnalytics(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
            .orElseThrow(() -> new RuntimeException("Faculty not found"));

        Double avgCommunication = facultyFeedbackRepository.getAverageCommunicationSkills(faculty);
        Double avgTeachingQuality = facultyFeedbackRepository.getAverageTeachingQuality(faculty);

        List<Object[]> categoryCounts = facultyFeedbackRepository.getFeedbackCategoryCounts(faculty);
        Map<String, Long> categoryDistribution = categoryCounts.stream()
            .collect(Collectors.toMap(
                category -> category[0].toString(),
                category -> (Long) category[1]
            ));

        return Map.of(
            "facultyId", faculty.getId(),
            "averageCommunicationSkills", avgCommunication,
            "averageTeachingQuality", avgTeachingQuality,
            "feedbackCategoryDistribution", categoryDistribution
        );
    }
}
