package com.example.PersonalWorkoutTracker.service;

import com.example.PersonalWorkoutTracker.model.Goal;
import com.example.PersonalWorkoutTracker.repository.GoalRepository;
import org.springframework.stereotype.Service;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    // Set weekly workout goal
    public Goal setGoal(Goal goal) {

        if (goal.getWorkoutCount() <= 0) {
            throw new IllegalArgumentException(
                    "Weekly workout goal must be greater than 0"
            );
        }

        return goalRepository.save(goal);
    }

    // Get goal by user
    public Goal getGoalByUser(Long userId) {

        return goalRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Goal not found for user ID: " + userId
                        ));
    }

    // Update goal
    public Goal updateGoal(Long id, Goal newGoal) {

        Goal existingGoal = goalRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Goal not found with ID: " + id
                        ));

        if (newGoal.getWorkoutCount() <= 0) {
            throw new IllegalArgumentException(
                    "Weekly workout goal must be greater than 0"
            );
        }

        existingGoal.setWorkoutCount(
                newGoal.getWorkoutCount()
        );

        return goalRepository.save(existingGoal);
    }

    // Delete goal
    public void deleteGoal(Long id) {

        if (!goalRepository.existsById(id)) {
            throw new RuntimeException(
                    "Goal not found with ID: " + id
            );
        }

        goalRepository.deleteById(id);
    }
}
