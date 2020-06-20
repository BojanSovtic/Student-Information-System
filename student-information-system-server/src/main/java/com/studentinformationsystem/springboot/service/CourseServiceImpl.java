package com.studentinformationsystem.springboot.service;

import com.studentinformationsystem.springboot.entity.Course;
import com.studentinformationsystem.springboot.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public Page<Course> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Course findCourseById(int theId) {
        return courseRepository.findById(theId).get();
    }

    @Override
    @Transactional
    public Course createCourse(Course tempCourse) {
        return courseRepository.save(tempCourse);
    }

    @Override
    @Transactional
    public Course updateCourse(Course newCourse, int theId) {
        return courseRepository.findById(theId).map(course -> {
            course.setTitle(newCourse.getTitle());
            course.setSemester(newCourse.getSemester());
            course.setYear(newCourse.getYear());
            course.setProfessor(newCourse.getProfessor());
            return courseRepository.save(course);
        }).orElseThrow(() -> new ResourceNotFoundException("Course id " + theId + " not found"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(int theId) {
        return courseRepository.findById(theId).map(course -> {
            courseRepository.delete(course);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Course id " + theId + " not found"));
    }
}