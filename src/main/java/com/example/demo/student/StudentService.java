package com.example.demo.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    public ArrayList<Student> studentsList = new ArrayList<>();


    public void addStudent(Student student){
        studentsList.add(student);
    }


    public List<Student> getStudents(){
        return studentsList;
    }

    public ResponseEntity<String> editStudent(long id, Student student) {
        for (Student s : studentsList) {
            if (s.getId() == id) {
                s.setName(student.getName());
                s.setAge(student.getAge());
                s.setDateOfBirth(student.getDateOfBirth());
                return ResponseEntity.ok("Student modified successfully!");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
    }

    public ResponseEntity<String> deleteStudent(long id){
        for (Student s : studentsList) {
            if (s.getId() == id) {
                studentsList.remove(s);
                return ResponseEntity.ok("Student deleted successfully!");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
    }
}
