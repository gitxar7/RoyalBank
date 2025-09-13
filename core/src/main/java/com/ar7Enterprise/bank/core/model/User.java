package com.ar7Enterprise.bank.core.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Cacheable(false)
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findByEmail", query = "select u from User u where u.email = ?1"),
        @NamedQuery(name = "User.findByEmailAndPassword",
                query = "select u from User u where u.email = :email and u.password =:password")
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String contact;
    @Column(unique = true)
    private String email;
    private String password;
    private String verification;
    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE;
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;
    private LocalDateTime creationDate = LocalDateTime.now();
    private LocalDateTime lastLoginDate;

    public User(String name, String contact, String email, String password, String verification, UserRole userRole) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.verification = verification;
        this.userRole = userRole;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
