package com.studentfeedbacksystem.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "faculty_feedback")
public class FacultyFeedback {
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public int getCommunicationSkills() {
		return communicationSkills;
	}

	public void setCommunicationSkills(int communicationSkills) {
		this.communicationSkills = communicationSkills;
	}

	public int getTeachingQuality() {
		return teachingQuality;
	}

	public void setTeachingQuality(int teachingQuality) {
		this.teachingQuality = teachingQuality;
	}

	public int getSubjectKnowledge() {
		return subjectKnowledge;
	}

	public void setSubjectKnowledge(int subjectKnowledge) {
		this.subjectKnowledge = subjectKnowledge;
	}

	public int getPunctuality() {
		return punctuality;
	}

	public void setPunctuality(int punctuality) {
		this.punctuality = punctuality;
	}

	public int getCourseOutcomeClarity() {
		return courseOutcomeClarity;
	}

	public void setCourseOutcomeClarity(int courseOutcomeClarity) {
		this.courseOutcomeClarity = courseOutcomeClarity;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDateTime getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(LocalDateTime submissionDate) {
		this.submissionDate = submissionDate;
	}

	public FeedbackCategory getCategory() {
		return category;
	}

	public void setCategory(FeedbackCategory category) {
		this.category = category;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Column(nullable = false)
    private int communicationSkills;

    @Column(nullable = false)
    private int teachingQuality;

    @Column(nullable = false)
    private int subjectKnowledge;

    @Column(nullable = false)
    private int punctuality;

    @Column(nullable = false)
    private int courseOutcomeClarity;

    @Column(length = 1000)
    private String comments;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    @Enumerated(EnumType.STRING)
    private FeedbackCategory category;

    // Enum for categorizing feedback
    public enum FeedbackCategory {
        EXCELLENT, GOOD, AVERAGE, NEEDS_IMPROVEMENT
    }

    // Constructors
    public FacultyFeedback() {}

    // Getters and Setters
}