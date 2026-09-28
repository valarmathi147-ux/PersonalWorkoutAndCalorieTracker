package com.example.PersonalWorkoutTracker.service;

import com.example.PersonalWorkoutTracker.model.Meal;
import com.example.PersonalWorkoutTracker.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    // Add meal
    public Meal addMeal(Meal meal) {

        // Business rule: calories consumed cannot be negative
        if (meal.getCalories() < 0) {
            throw new IllegalArgumentException(
                    "Calories consumed cannot be negative"
            );
        }

        // Quantity must be positive
        if (meal.getQuantity() <= 0) {
            throw new IllegalArgumentException(
                    "Meal quantity must be greater than 0"
            );
        }

        return mealRepository.save(meal);
    }

    // Get all meals
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    // Get meals by user
    public List<Meal> getMealsByUser(Long userId) {
        return mealRepository.findByUserId(userId);
    }

    // Get meal by ID
    public Meal getMealById(Long id) {

        return mealRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Meal not found with ID: " + id
                        ));
    }

    // Delete meal
    public void deleteMeal(Long id) {

        if (!mealRepository.existsById(id)) {
            throw new RuntimeException(
                    "Meal not found with ID: " + id
            );
        }

        mealRepository.deleteById(id);
    }
}
