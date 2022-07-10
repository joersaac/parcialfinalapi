package com.grupo16.src.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name="movie")
public class Movie {

    @Id
    @Column(name="code")
    private String code;

    @Column(name="title")
    private String title;

    @Column(name="length")
    private int length;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    private Category category;


    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JsonIgnore
    private List<Schedule>schedules;

    public Movie() {
        super();
    }

    public Movie(String code, String title, int length, Category category) {
        super();
        this.code = code;
        this.title = title;
        this.length = length;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
