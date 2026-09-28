package com.example.PersonalWorkoutTracker.service;

import com.example.PersonalWorkoutTracker.model.Workout;
import com.example.PersonalWorkoutTracker.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    // Add workout
    public Workout addWorkout(Workout workout) {

        // Business rule: calories burned cannot be negative
        if (workout.getCaloriesBurned() < 0) {
            throw new IllegalArgumentException(
                    "Calories burned cannot be negative"
            );
        }

        // Duration must be positive
        if (workout.getDuration() <= 0) {
            throw new IllegalArgumentException(
                    "Workout duration must be greater than 0"
            );
        }

        return workoutRepository.save(workout);
    }

    // Get all workouts
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    // Get workouts by user
    public List<Workout> getWorkoutsByUser(Long userId) {
        return workoutRepository.findByUserId(userId);
    }

    // Get workout by ID
    public Workout getWorkoutById(Long id) {

        return workoutRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Workout not found with ID: " + id
                        ));
    }

    // Delete workout
    public void deleteWorkout(Long id) {

        if (!workoutRepository.existsById(id)) {
            throw new RuntimeException(
                    "Workout not found with ID: " + id
            );
        }

        workoutRepository.deleteById(id);
    }
}