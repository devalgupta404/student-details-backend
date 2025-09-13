package com.devalgupta4.gmail.com.student.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
 @Getter
 @Setter
public class StudentDto {
    private Long id;
    private String name;
    private String email;
}

