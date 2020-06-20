package com.studentinformationsystem.springboot.repository;

import com.studentinformationsystem.springboot.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("http://localhost:4200")
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

    public Page<Student> findByEmailContaining(String email, Pageable pageable);
}
