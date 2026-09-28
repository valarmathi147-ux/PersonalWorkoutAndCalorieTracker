package com.example.PersonalWorkoutTracker.controller;

import com.example.PersonalWorkoutTracker.service.SummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/summary")
public class SummaryController {

    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    // Get daily calorie summary
    @GetMapping("/daily/{userId}")
    public ResponseEntity<Map<String, Object>> getDailySummary(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                summaryService.getDailySummary(userId)
        );
    }

    // Get weekly workout trend
    @GetMapping("/weekly/{userId}")
    public ResponseEntity<Map<String, Object>> getWeeklySummary(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                summaryService.getWeeklySummary(userId)
        );
    }
}