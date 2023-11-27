package com.example.demo.student;
import java.time.LocalDate;

public record StudentDTO(
        long id,
        String name,
        String email,
        LocalDate birthDate,
        int age
) {

}
