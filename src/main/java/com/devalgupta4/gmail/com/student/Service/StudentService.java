package com.devalgupta4.gmail.com.student.Service;

import com.devalgupta4.gmail.com.student.dto.AddStudentRequestDto;
import com.devalgupta4.gmail.com.student.dto.StudentDto;

import java.util.List;

public interface StudentService {
     StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) ;
    List<StudentDto> getAllStudents() ;

    StudentDto getStudentById(Long id);
}
