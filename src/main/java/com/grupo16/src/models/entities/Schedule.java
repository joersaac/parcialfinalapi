package com.grupo16.src.models.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="schedule")
public class Schedule {

    @Id
    @Column(name="id")
    @SequenceGenerator(name="schedule_id_gen",sequenceName = "schedule_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "schedule_id_gen")
    private long id;

    @Column(name="timestamp")
    private Timestamp timestamp;

    @Column(name="price")
    private long price;

    @Column(name="capacity")
    private int capacity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_movie")
    private Movie movie;

    public Schedule() {
        super();
    }

    public Schedule(long id, Timestamp time, long price, int capacity, Movie movie) {
        super();
        this.id = id;
        this.timestamp = time;
        this.price = price;
        this.capacity = capacity;
        this.movie = movie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
