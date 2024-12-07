package com.studentfeedbacksystem.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "course_feedback")
public class CourseFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long courseId;
    private Long studentId;
    private Integer teaching;
    private Integer content;
    private Integer materials;
    private Integer engagement;
    private Integer satisfaction;
    private String comments;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    
    public Integer getTeaching() { return teaching; }
    public void setTeaching(Integer teaching) { this.teaching = teaching; }
    
    public Integer getContent() { return content; }
    public void setContent(Integer content) { this.content = content; }
    
    public Integer getMaterials() { return materials; }
    public void setMaterials(Integer materials) { this.materials = materials; }
    
    public Integer getEngagement() { return engagement; }
    public void setEngagement(Integer engagement) { this.engagement = engagement; }
    
    public Integer getSatisfaction() { return satisfaction; }
    public void setSatisfaction(Integer satisfaction) { this.satisfaction = satisfaction; }
    
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
	