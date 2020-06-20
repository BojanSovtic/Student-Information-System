package com.studentinformationsystem.springboot.service;

import com.studentinformationsystem.springboot.repository.ProfessorDAO;
import com.studentinformationsystem.springboot.entity.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private ProfessorDAO professorDAO;

    @Autowired
    public ProfessorServiceImpl(ProfessorDAO professorDAO) {
        this.professorDAO = professorDAO;
    }

    @Override
    @Transactional
    public List<Professor> findAll() {
        return professorDAO.findAll();
    }

    @Override
    @Transactional
    public Professor findById(int theId) {
        return professorDAO.findById(theId);
    }

    @Override
    @Transactional
    public Professor save(Professor tempProfessor) {
        professorDAO.save(tempProfessor);
        return tempProfessor;
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        professorDAO.deleteById(theId);
    }
}
