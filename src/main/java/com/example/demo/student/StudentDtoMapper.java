package com.example.demo.student;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentDtoMapper {

    public StudentDTO mapToDto(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getBirthDate(),
                student.getAge());
    }
}
