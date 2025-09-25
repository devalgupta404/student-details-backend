package com.devalgupta4.gmail.com.student.Service.impl;

import com.devalgupta4.gmail.com.student.Service.StudentService;
import com.devalgupta4.gmail.com.student.dto.AddStudentRequestDto;
import com.devalgupta4.gmail.com.student.dto.StudentDto;
import com.devalgupta4.gmail.com.student.entity.User;
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
        User newUser = modelMapper.map(addStudentRequestDto, User.class);
        User user = studentRepository.save(newUser);
        return modelMapper.map(user, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<User> users = studentRepository.findAll();
        return users
                .stream()
                .map(user -> modelMapper.map(user, StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        User user = studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found by ID: "+id));
        return modelMapper.map(user, StudentDto.class);
    }

    @Override
    public void deleteStudentByid(Long id) {
        if(!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student does not exists by id: "+id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        User user = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(("Student not found by id : "+id)));
        modelMapper.map(addStudentRequestDto, user);
        user = studentRepository.save(user);
        return modelMapper.map(user, StudentDto.class);
    }
}
