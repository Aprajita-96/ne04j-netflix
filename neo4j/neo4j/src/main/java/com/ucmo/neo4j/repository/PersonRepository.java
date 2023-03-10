package com.ucmo.neo4j.repository;

import com.ucmo.neo4j.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends Neo4jRepository<Person,String> {
    @Query("Match (m:Movie{title:($title)})<-[r]-(p:Person)" +
            "return p;")
    List<Person> getCast(String title);
}
