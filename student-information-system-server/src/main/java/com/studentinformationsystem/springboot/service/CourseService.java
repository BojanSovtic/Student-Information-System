package com.studentinformationsystem.springboot.service;

import com.studentinformationsystem.springboot.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CourseService {

    public Page<Course> getAllCourses(Pageable pageable);

    public Course findCourseById(int id);

    public Course createCourse(Course course);

    public Course updateCourse(Course course, int theId);

    public ResponseEntity<?> deleteById(int theId);
}
