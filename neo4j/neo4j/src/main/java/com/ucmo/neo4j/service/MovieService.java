package com.ucmo.neo4j.service;

import com.ucmo.neo4j.Movie;
import com.ucmo.neo4j.Response;
import com.ucmo.neo4j.repository.CountryRepository;
import com.ucmo.neo4j.repository.MovieRepository;
import com.ucmo.neo4j.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repo;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private PersonRepository personRepository;
    public Movie saveMovie(Movie movie){
        List<Movie> m=repo.savemovie(movie.getTitle(),movie.getShow_id(),movie.getType(),movie.getRelease_year(),
        movie.getRating(), movie.getDuration(),movie.getDescription());
        return m.get(0);
    }
    public Movie updateMovie(String title,Movie movie){
        System.out.println(
        repo.getMovieByTitle(title));
        Movie mov=null;
        Movie existingMovie= repo.getMovieByTitle(title);
        if(existingMovie==null){
            return mov;
        }
        String newTitle=movie.getTitle()==null?title:movie.getTitle();
        String description= movie.getDescription()==null?existingMovie.getDescription():movie.getDescription();
        String rating=movie.getRating()==null?existingMovie.getRating():movie.getRating();
        List<Movie> m= repo.updateMovie(title,newTitle,description,rating);
        return m.isEmpty() ? mov : m.get(0);
    }

    public void deleteMovieAndRelationships(String title){
        repo.deleteRelationshipsMovieByTitle(title);
        repo.deleteMovieByTitle(title);
    }

     public List<List<Movie>> getall(){
        return repo.getAll();
     }

     public Response getMovieAndDescription(String title){
         Response response=new Response();
         response.setMovies(repo.getMovieByTitle(title));
         response.setCountries(countryRepository.getCountry(title));
         response.setPeople(personRepository.getCast(title));

        return response;
     }

}
