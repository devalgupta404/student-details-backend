package com.devalgupta4.gmail.com.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String collegeDomain;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;
    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.collegeDomain = extractCollegeDomain(email);
    }

    private String extractCollegeDomain(String email) {
        if (email != null && email.contains("@")) {
            return email.substring(email.indexOf("@") + 1);
        }
        return null;
    }
}
