package com.devalgupta4.gmail.com.student.Service.impl;

import com.devalgupta4.gmail.com.student.Service.StudentService;
import com.devalgupta4.gmail.com.student.dto.AddStudentRequestDto;
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
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto,Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student>students = studentRepository.findAll();
        return students
                .stream()
                .map(student -> modelMapper.map(student,StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found by IB: "+id));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudentByid(Long id) {
        if(!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student does not exists by id: "+id);
        }
        studentRepository.deleteById(id);
    }
}
