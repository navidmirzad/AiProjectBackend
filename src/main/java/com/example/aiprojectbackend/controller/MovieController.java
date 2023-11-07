package com.example.aiprojectbackend.controller;

import com.example.aiprojectbackend.dto.MovieDTO;
import com.example.aiprojectbackend.entity.Genre;
import com.example.aiprojectbackend.entity.Movie;
import com.example.aiprojectbackend.repository.GenreRepository;
import com.example.aiprojectbackend.repository.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;


    private final String requestUrl = "https://imdb-top-100-movies.p.rapidapi.com/";
    private final String apiName = "X-RapidAPI-Key";
    private final String apiKey = "c72c895b0cmsh0dc387c464f5dffp1047cdjsn0a9a6d43c9fe";
    private final String hostName = "X-RapidAPI-Host";
    private final String hostKey = "imdb-top-100-movies.p.rapidapi.com";


    @GetMapping("/api/movies")
    public List<Movie> getMoviesAPI() {
        List<Movie> movies = new ArrayList<>();

        try {
            WebClient webClient = WebClient.builder()
                    .baseUrl(requestUrl)
                    .defaultHeader(apiName, apiKey)
                    .defaultHeader(hostName, hostKey)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            String jsonData = webClient
                    .get()
                    .exchange()
                    .block()
                    .bodyToMono(String.class)
                    .block();

            ObjectMapper objectMapper = new ObjectMapper();
            List<MovieDTO> movieDTOS = objectMapper.readValue(jsonData, new TypeReference<List<MovieDTO>>() {});

            for (MovieDTO movieData : movieDTOS) {
                Movie movie = new Movie();
                movie.setTitle(movieData.getTitle());

                for (String genreName : movieData.getGenres()) {
                    Genre genre = genreRepository.findByGenreName(genreName);
                    if (genre == null) {
                        genre = new Genre();
                        genre.setGenreName(genreName);
                        genreRepository.save(genre);
                    }
                    movie.getGenres().add(genre);
                }

                // Save the movie entity
                movieRepository.save(movie);
                movies.add(movie);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return movies;
    }

    @GetMapping("/db/movies")
    public ResponseEntity<List<Movie>> getMoviesDB() {
        List<Movie> movies = movieRepository.findAll();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/db/genres")
    public ResponseEntity<List<Genre>> getGenresDB() {
        List<Genre> genres = genreRepository.findAll();
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

}
