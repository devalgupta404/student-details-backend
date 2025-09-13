package com.devalgupta4.gmail.com.student.controller;

import com.devalgupta4.gmail.com.student.Service.StudentService;
import com.devalgupta4.gmail.com.student.dto.AddStudentRequestDto;
import com.devalgupta4.gmail.com.student.dto.StudentDto;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
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
