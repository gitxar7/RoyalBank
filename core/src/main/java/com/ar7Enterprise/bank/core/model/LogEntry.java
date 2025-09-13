package com.ar7Enterprise.bank.core.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Cacheable(false)
@Entity
@Table(name = "logs")
public class LogEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String method;
    private String className;
    private String parameters;
    private LocalDateTime timestamp;
    @ManyToOne
    private User user;
    private String actionType;
    private boolean success;
    private String message;

    public LogEntry(String method, String className, String parameters, LocalDateTime timestamp, User user, String actionType, boolean success, String message) {
        this.method = method;
        this.className = className;
        this.parameters = parameters;
        this.timestamp = timestamp;
        this.user = user;
        this.actionType = actionType;
        this.success = success;
        this.message = message;
    }

    public LogEntry() {
    }

    public Long getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

