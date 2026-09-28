package com.example.PersonalWorkoutTracker.repository;

import com.example.PersonalWorkoutTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
