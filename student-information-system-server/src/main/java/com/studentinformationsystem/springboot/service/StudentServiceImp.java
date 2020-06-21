package com.studentinformationsystem.springboot.service;

import com.studentinformationsystem.springboot.entity.Student;
import com.studentinformationsystem.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Student findStudentById(int theId) {
        return studentRepository.findById(theId).get();
    }

    @Override
    @Transactional
    public Student createStudent(Student tempStudent) {
        return studentRepository.save(tempStudent);
    }

    @Override
    @Transactional
    public Student updateStudent(Student newStudent, Integer theId) {
        return studentRepository.findById(theId).map(student -> {
            student.setFirstName(newStudent.getFirstName());
            student.setLastName(newStudent.getLastName());
            student.setIndexNumber(newStudent.getIndexNumber());
            student.setEmail(newStudent.getEmail());
            student.setCourses(newStudent.getCourses());
            return studentRepository.save(student);
        }).orElseThrow(() -> new ResourceNotFoundException("Student id " + theId + " not found"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(int theId) {
        return studentRepository.findById(theId).map(student -> {
            studentRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Student id " + theId + " not found"));
    }


    @Override
    @Transactional
    public Page<Student> findByEmailContaining(String email, Pageable pageable) {
        return studentRepository.findByEmailContaining(email, pageable);
    };
}
