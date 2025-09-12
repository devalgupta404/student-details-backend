package com.devalgupta4.gmail.com.student.repository;

import com.devalgupta4.gmail.com.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

}
