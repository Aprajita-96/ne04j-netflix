package com.ucmo.neo4j.repository;
import com.ucmo.neo4j.Country;
import com.ucmo.neo4j.Movie;
import com.ucmo.neo4j.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie,String> {

    @Query("MATCH(m:Movie{title:($title)}) return m;")
    Movie getMovieByTitle(String title);
    @Query("Merge(m:Movie{title:($title)}) " + "\n"+
            "set m.show_id=($show_id),m.type=($type),m.release_year=($release_year)," +
            " m.rating=($rating),m.duration=($duration),m.description=($description) return m;")
    List<Movie> savemovie(String title, String show_id, String type, String release_year, String rating, String duration, String description);

    @Query("MATCH(m:Movie{title:($title)})" + "\n"+
            "set m.title=($newTitle),m.description=($description),m.rating=($rating) " +
            "return m")
    List<Movie> updateMovie(String title,String newTitle,String description,String rating);

    @Query("Match (:Country)<-[s]-(m:movie)<-[r]-(:Person)\n" +
            "Where m.title=($title)\n" +
            "Delete r,s;\n")
    void deleteRelationshipsMovieByTitle(String title);
    void deleteMovieByTitle(String title);

    @Query("Match (m:Movie) return m;")
    List<List<Movie>> getAll();




}
