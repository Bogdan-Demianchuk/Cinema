package com.cinema.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MovieRequestDto {
    @NotNull(message = "Movie title can't be null")
    @Size(min = 2, message = "Movie title length should be 2 or more symbols")
    private String title;
    @NotNull(message = "Movie description can't be null")
    @Size(min = 2, message = "Movie description length should be 2 or more symbols")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
