package com.devalgupta4.gmail.com.student.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Getter
@Setter
public class Student {

    private Long id;

    private String name;

    private String email;
}
