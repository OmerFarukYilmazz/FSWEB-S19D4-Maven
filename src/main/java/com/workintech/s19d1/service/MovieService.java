package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(long id);
    Movie save(Movie actor);
    void delete(Movie actor);
}
