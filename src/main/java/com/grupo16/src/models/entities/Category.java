package com.grupo16.src.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name="category")
public class Category {
    @Id
    @Column(name="code")
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JsonIgnore
    private List<Movie> movies;

    public Category(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Category() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
