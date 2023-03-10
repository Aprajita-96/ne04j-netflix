package com.ucmo.neo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node(labels = "Movie")
public class Movie {
    @Id
    private String id= UUID.randomUUID().toString();
    private String show_id;
    private String description;
    private String rating;
    private String  release_year;
    private String title;
    private String type;
    private String duration;
}
