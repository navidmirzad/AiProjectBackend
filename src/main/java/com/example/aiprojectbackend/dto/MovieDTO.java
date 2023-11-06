package com.example.aiprojectbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = {"rank", "description", "image", "big_image", "thumbnail",
        "thumbnail", "rating", "id", "year", "imdbid", "imdb_link"})
public class MovieDTO {

    private String title;
    @JsonProperty("genre")
    private List<String> genres = new ArrayList<>();

    public MovieDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
