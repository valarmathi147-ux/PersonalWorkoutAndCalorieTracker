package com.example.PersonalWorkoutTracker.repository;

import com.example.PersonalWorkoutTracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    // Get all workouts of a particular user
    List<Workout> findByUserId(Long userId);

    // Get workouts of a user between two dates
    List<Workout> findByUserIdAndWorkoutDateBetween(
            Long userId,
            LocalDate startDate,
            LocalDate endDate
    );
}