package com.ar7Enterprise.bank.core.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Cacheable(false)
@Entity
public class History implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    @Column(name = "DATETIME")
    private LocalDateTime dateTime;
    @ManyToOne
    private User user;

    public History(String function, String description, LocalDateTime dateTime, User user) {
        this.title = function;
        this.description = description;
        this.dateTime = dateTime;
        this.user = user;
    }
    public History() {}

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
