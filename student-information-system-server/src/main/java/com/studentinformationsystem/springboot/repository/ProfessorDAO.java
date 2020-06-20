package com.studentinformationsystem.springboot.repository;

import com.studentinformationsystem.springboot.entity.Professor;

import java.util.List;

public interface ProfessorDAO {

    public List<Professor> findAll();
    public Professor findById(int theId);
    public void save(Professor tempProfessor);
    public void deleteById(int theId);

}
