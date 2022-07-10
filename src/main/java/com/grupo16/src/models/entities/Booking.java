package com.grupo16.src.models.entities;

import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="booking")
public class Booking {

    @Id
    @Column(name="id")
    @SequenceGenerator(name="booking_id_gen",sequenceName = "booking_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "booking_id_gen")
    private long id;

    @Column(name="timestamp")
    private Timestamp time;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_schedule")
    private Schedule schedule;

    public Booking() {
        super();
    }

    public Booking(long id, Timestamp time, int quantity, User user, Schedule schedule) {
        super();
        this.id = id;
        this.time = time;
        this.quantity = quantity;
        this.user = user;
        this.schedule = schedule;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
