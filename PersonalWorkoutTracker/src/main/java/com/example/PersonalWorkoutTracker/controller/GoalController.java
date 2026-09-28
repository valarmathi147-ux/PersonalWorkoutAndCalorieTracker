package com.example.PersonalWorkoutTracker.controller;

import com.example.PersonalWorkoutTracker.model.Goal;
import com.example.PersonalWorkoutTracker.service.GoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    // Set weekly workout goal
    @PostMapping
    public ResponseEntity<Goal> setGoal(@RequestBody Goal goal) {
        Goal savedGoal = goalService.setGoal(goal);
        return new ResponseEntity<>(savedGoal, HttpStatus.CREATED);
    }

    // Get user's goal
    @GetMapping("/user/{userId}")
    public ResponseEntity<Goal> getGoalByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                goalService.getGoalByUser(userId)
        );
    }

    // Update goal
    @PutMapping("/{id}")
    public ResponseEntity<Goal> updateGoal(
            @PathVariable Long id,
            @RequestBody Goal goal) {

        return ResponseEntity.ok(
                goalService.updateGoal(id, goal)
        );
    }

    // Delete goal
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGoal(
            @PathVariable Long id) {

        goalService.deleteGoal(id);
        return ResponseEntity.ok("Goal deleted successfully");
    }
}
