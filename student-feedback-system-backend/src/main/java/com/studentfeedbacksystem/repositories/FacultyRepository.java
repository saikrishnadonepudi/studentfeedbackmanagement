package com.studentfeedbacksystem.repositories;

import com.studentfeedbacksystem.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Optional<Faculty> findByEmail(String email);

    List<Faculty> findByDepartment(String department);

    @Query("SELECT f FROM Faculty f WHERE f.designation = :designation")
    List<Faculty> findByDesignation(String designation);

    @Query("SELECT f FROM Faculty f WHERE f.firstName LIKE %:name% OR f.lastName LIKE %:name%")
    List<Faculty> searchByName(String name);
}