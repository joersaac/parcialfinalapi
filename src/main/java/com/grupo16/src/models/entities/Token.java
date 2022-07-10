package com.grupo16.src.models.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "token")
public class Token {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "token_id_gen", sequenceName = "token_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_id_gen")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "active", insertable = false)
    private Boolean active;

    @Column(name = "timestamp", insertable = false, updatable = false)
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    public Token() {
        super();
    }

    public Token(String content, User id_user) {
        super();
        this.content = content;
        this.user = id_user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
