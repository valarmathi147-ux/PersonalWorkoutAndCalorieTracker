package com.example.PersonalWorkoutTracker.controller;

import com.example.PersonalWorkoutTracker.model.Workout;
import com.example.PersonalWorkoutTracker.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    // Add a workout
    @PostMapping
    public ResponseEntity<Workout> addWorkout(@RequestBody Workout workout) {
        Workout savedWorkout = workoutService.addWorkout(workout);
        return new ResponseEntity<>(savedWorkout, HttpStatus.CREATED);
    }

    // Get all workouts
    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

    // Get workouts of a particular user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Workout>> getWorkoutsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                workoutService.getWorkoutsByUser(userId)
        );
    }

    // Get workout by ID
    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                workoutService.getWorkoutById(id)
        );
    }

    // Delete workout
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkout(
            @PathVariable Long id) {

        workoutService.deleteWorkout(id);
        return ResponseEntity.ok("Workout deleted successfully");
    }
}