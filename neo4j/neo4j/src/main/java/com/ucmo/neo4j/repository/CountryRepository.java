package com.ucmo.neo4j.repository;

import com.ucmo.neo4j.Country;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface CountryRepository extends Neo4jRepository<Country,String> {
    @Query("Match(c:Country)<-[d]-(m:Movie{title:($title)})" +
            "return c;")
    List<Country> getCountry(String title);
}
