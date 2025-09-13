package com.devalgupta4.gmail.com.student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddStudentRequestDto {
    @NotBlank(message = "name is required")
    @Size(min = 3,max= 30,message = "name should have minnimum 3 letters and maximum 30 letters")
    private String name;
@Email
@NotBlank(message = "email is required")
    private String email;
}
