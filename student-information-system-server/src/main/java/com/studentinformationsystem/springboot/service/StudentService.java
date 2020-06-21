package com.studentinformationsystem.springboot.service;

import com.studentinformationsystem.springboot.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StudentService {

    public Page<Student> getAllStudents(Pageable pageable);

    public Student findStudentById(int id);

    public Student createStudent(Student student);

    public Student updateStudent(Student student, Integer theId);

    public ResponseEntity<?> deleteById(int theId);

    public Page<Student> findByEmailContaining(String email, Pageable pageable);

}
