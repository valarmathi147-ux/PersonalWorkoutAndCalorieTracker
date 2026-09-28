package com.example.PersonalWorkoutTracker.controller;

import com.example.PersonalWorkoutTracker.model.Meal;
import com.example.PersonalWorkoutTracker.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    // Add a meal
    @PostMapping
    public ResponseEntity<Meal> addMeal(@RequestBody Meal meal) {
        Meal savedMeal = mealService.addMeal(meal);
        return new ResponseEntity<>(savedMeal, HttpStatus.CREATED);
    }

    // Get all meals
    @GetMapping
    public ResponseEntity<List<Meal>> getAllMeals() {
        return ResponseEntity.ok(mealService.getAllMeals());
    }

    // Get meals of a particular user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Meal>> getMealsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                mealService.getMealsByUser(userId)
        );
    }

    // Get meal by ID
    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                mealService.getMealById(id)
        );
    }

    // Delete meal
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMeal(
            @PathVariable Long id) {

        mealService.deleteMeal(id);
        return ResponseEntity.ok("Meal deleted successfully");
    }
}