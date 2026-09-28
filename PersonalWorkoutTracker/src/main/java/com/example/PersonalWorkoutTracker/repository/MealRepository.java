package com.example.PersonalWorkoutTracker.repository;

import com.example.PersonalWorkoutTracker.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    // Get all meals of a particular user
    List<Meal> findByUserId(Long userId);

    // Get meals of a user between two dates
    List<Meal> findByUserIdAndMealDateBetween(
            Long userId,
            LocalDate startDate,
            LocalDate endDate
    );
}
