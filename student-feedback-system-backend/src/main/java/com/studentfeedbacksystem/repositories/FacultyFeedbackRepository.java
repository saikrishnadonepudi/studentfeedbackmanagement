package com.studentfeedbacksystem.repositories;

import com.studentfeedbacksystem.models.Faculty;
import com.studentfeedbacksystem.models.FacultyFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyFeedbackRepository extends JpaRepository<FacultyFeedback, Long> {
    List<FacultyFeedback> findByFaculty(Faculty faculty);

    @Query("SELECT AVG(f.communicationSkills) FROM FacultyFeedback f WHERE f.faculty = :faculty")
    Double getAverageCommunicationSkills(Faculty faculty);

    @Query("SELECT AVG(f.teachingQuality) FROM FacultyFeedback f WHERE f.faculty = :faculty")
    Double getAverageTeachingQuality(Faculty faculty);

    @Query("SELECT f.category, COUNT(f) FROM FacultyFeedback f WHERE f.faculty = :faculty GROUP BY f.category")
    List<Object[]> getFeedbackCategoryCounts(Faculty faculty);
}