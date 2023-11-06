package com.example.aiprojectbackend.repository;

import com.example.aiprojectbackend.entity.Genre;
import com.example.aiprojectbackend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    Genre findByGenreName(String genreName);
}
