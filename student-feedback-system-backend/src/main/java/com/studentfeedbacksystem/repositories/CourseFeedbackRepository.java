package com.studentfeedbacksystem.repositories;

import com.studentfeedbacksystem.models.CourseFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseFeedbackRepository extends JpaRepository<CourseFeedback, Long> {
    List<CourseFeedback> findByCourseId(Long courseId);
    
    @Query("SELECT COALESCE(AVG(f.teaching), 0), " +
    	       "COALESCE(AVG(f.content), 0), " +
    	       "COALESCE(AVG(f.materials), 0), " +
    	       "COALESCE(AVG(f.engagement), 0), " +
    	       "COALESCE(AVG(f.satisfaction), 0), " +
    	       "COALESCE(COUNT(f.id), 0) " +
    	       "FROM CourseFeedback f WHERE f.courseId = ?1")
    	Object[] getAverageRatingsByCourseId(Long courseId);

}
