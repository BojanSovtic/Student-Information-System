package com.studentinformationsystem.springboot.service;

import com.studentinformationsystem.springboot.entity.Professor;

import java.util.List;

public interface ProfessorService {

    public List<Professor> findAll();
    public Professor findById(int theId);
    public Professor save(Professor tempProfessor);
    public void deleteById(int theId);
}
