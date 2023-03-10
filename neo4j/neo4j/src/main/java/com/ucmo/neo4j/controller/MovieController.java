package com.ucmo.neo4j.controller;

import com.ucmo.neo4j.Movie;
import com.ucmo.neo4j.Response;
import com.ucmo.neo4j.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("movie/v1")
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping("/title")
    private ResponseEntity<Movie> saveMovie( @RequestBody Movie movie){
        return new ResponseEntity<>(service.saveMovie(movie), HttpStatus.CREATED);
    }
    @PatchMapping("/title/{title}")
    private ResponseEntity<Movie> updateMovie(@PathVariable("title") String title, @RequestBody Movie movie){
        Movie m=service.updateMovie(title,movie);
        if(m==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(m, HttpStatus.OK);
    }
    @DeleteMapping("/title/{title}")
    private ResponseEntity<?> deleteMovie(@PathVariable("title") String title){
        service.deleteMovieAndRelationships(title);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
    @GetMapping("/title")
    private ResponseEntity<List<?>> getAllMovies(){
        return new ResponseEntity<>(service.getall(),HttpStatus.OK);
    }
    @GetMapping("/title/{title}")
    private ResponseEntity<Response> getMovie(@PathVariable("title") String title){
        return new ResponseEntity<>(service.getMovieAndDescription(title),HttpStatus.OK);
    }
}
