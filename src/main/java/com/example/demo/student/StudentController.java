package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully!");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable long id, @RequestBody Student student){
         return studentService.editStudent(id, student);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id){
        return studentService.deleteStudent(id);
    }
}
