package com.devalgupta4.gmail.com.student.controller;

import com.devalgupta4.gmail.com.student.Service.StudentService;
import com.devalgupta4.gmail.com.student.dto.StudentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public ResponseEntity<List<StudentDto>>getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto>getStudentById(@PathVariable Long id){
       return  ResponseEntity.ok(studentService.getStudentById(id));
    }
}
