package com.example.aiprojectbackend.repository;

import com.example.aiprojectbackend.entity.Genre;
import com.example.aiprojectbackend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
