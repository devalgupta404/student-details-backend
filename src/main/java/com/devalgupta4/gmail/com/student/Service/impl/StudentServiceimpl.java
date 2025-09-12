package com.devalgupta4.gmail.com.student.Service.impl;

import com.devalgupta4.gmail.com.student.Service.StudentService;
import com.devalgupta4.gmail.com.student.dto.StudentDto;
import com.devalgupta4.gmail.com.student.entity.Student;
import com.devalgupta4.gmail.com.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceimpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student>students = studentRepository.findAll();
        return students
                .stream()
                .map(student -> modelMapper.map(student,StudentDto.class))
                .toList();
    }
}
