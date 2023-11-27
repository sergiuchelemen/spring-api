package com.example.demo.student;

import com.example.demo.exception.ResourceConflictException;
import com.example.demo.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentDtoMapper studentDtoMapper;
    @Autowired
    public StudentService(StudentRepository studentRepository, StudentDtoMapper studentDtoMapper){
        this.studentRepository = studentRepository;
        this.studentDtoMapper = studentDtoMapper;
    }

    public List<StudentDTO> getStudents() {
        return studentRepository.findAll().stream()
                .map(studentDtoMapper::mapToDto).toList();
    }

    public void addStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new ResourceConflictException("Email is already used.");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        boolean studentExists = studentRepository.existsById(id);
        if(!studentExists){
            throw new ResourceNotFoundException("Student not found!");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(long id, String newName, String newEmail){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isEmpty()){
            throw new IllegalStateException("Student doesn't exist.");
        }

        Student student = studentOptional.get();
        student.setEmail(newEmail);
        student.setName(newName);
    }
}
