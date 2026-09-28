package com.example.PersonalWorkoutTracker.service;

import com.example.PersonalWorkoutTracker.model.Meal;
import com.example.PersonalWorkoutTracker.model.Workout;
import com.example.PersonalWorkoutTracker.repository.MealRepository;
import com.example.PersonalWorkoutTracker.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SummaryService {

    private final WorkoutRepository workoutRepository;
    private final MealRepository mealRepository;

    public SummaryService(
            WorkoutRepository workoutRepository,
            MealRepository mealRepository) {

        this.workoutRepository = workoutRepository;
        this.mealRepository = mealRepository;
    }

    // Daily calorie summary
    public Map<String, Object> getDailySummary(Long userId) {

        LocalDate today = LocalDate.now();

        List<Workout> workouts =
                workoutRepository.findByUserIdAndWorkoutDateBetween(
                        userId,
                        today,
                        today
                );

        List<Meal> meals =
                mealRepository.findByUserIdAndMealDateBetween(
                        userId,
                        today,
                        today
                );

        // Check whether at least one entry exists
        if (workouts.isEmpty() && meals.isEmpty()) {
            throw new RuntimeException(
                    "No workout or meal entries found for today"
            );
        }

        double caloriesBurned = workouts.stream()
                .mapToDouble(Workout::getCaloriesBurned)
                .sum();

        double caloriesConsumed = meals.stream()
                .mapToDouble(Meal::getCalories)
                .sum();

        Map<String, Object> summary = new HashMap<>();

        summary.put("date", today);
        summary.put("caloriesConsumed", caloriesConsumed);
        summary.put("caloriesBurned", caloriesBurned);
        summary.put(
                "netCalories",
                caloriesConsumed - caloriesBurned
        );

        return summary;
    }

    // Weekly workout summary
    public Map<String, Object> getWeeklySummary(Long userId) {

        LocalDate today = LocalDate.now();

        LocalDate startOfWeek =
                today.with(DayOfWeek.MONDAY);

        LocalDate endOfWeek =
                today.with(DayOfWeek.SUNDAY);

        List<Workout> workouts =
                workoutRepository.findByUserIdAndWorkoutDateBetween(
                        userId,
                        startOfWeek,
                        endOfWeek
                );

        List<Meal> meals =
                mealRepository.findByUserIdAndMealDateBetween(
                        userId,
                        startOfWeek,
                        endOfWeek
                );

        // Weekly summary only if at least one entry exists
        if (workouts.isEmpty() && meals.isEmpty()) {
            throw new RuntimeException(
                    "No workout or meal entries found for this week"
            );
        }

        double totalCaloriesBurned = workouts.stream()
                .mapToDouble(Workout::getCaloriesBurned)
                .sum();

        double totalCaloriesConsumed = meals.stream()
                .mapToDouble(Meal::getCalories)
                .sum();

        Map<String, Object> summary = new HashMap<>();

        summary.put("weekStart", startOfWeek);
        summary.put("weekEnd", endOfWeek);
        summary.put("workoutsCompleted", workouts.size());
        summary.put("totalCaloriesBurned", totalCaloriesBurned);
        summary.put("totalCaloriesConsumed", totalCaloriesConsumed);

        return summary;
    }
}
