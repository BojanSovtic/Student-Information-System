package com.studentinformationsystem.springboot.repository;

import com.studentinformationsystem.springboot.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("http://localhost:4200")
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
