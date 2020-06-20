package com.studentinformationsystem.springboot.repository;

import com.studentinformationsystem.springboot.entity.Professor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@CrossOrigin("http://localhost:4200")
public class ProfessorDAOImpl implements ProfessorDAO {

    private EntityManager entityManager;

    @Autowired
    public ProfessorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Professor> findAll() {

        Session currentSession = getSession();

        Query<Professor> query = currentSession.createQuery("from Professor", Professor.class);

        List<Professor> professors = query.getResultList();

        return professors;
    }

    @Override
    public Professor findById(int theId) {

        Session currentSession = getSession();

        Professor tempProfessor = currentSession.get(Professor.class, theId);

        return tempProfessor;
    }


    @Override
    public void save(Professor tempProfessor) {

        Session currentSession = getSession();

        currentSession.saveOrUpdate(tempProfessor);
    }

    @Override
    public void deleteById(int theId) {

        Session currentSession = getSession();

        Query query = currentSession.createQuery("DELETE FROM Professor WHERE professor_id=:professorId");
        query.setParameter("professorId", theId);

        query.executeUpdate();
    }

    private Session getSession() {

        return entityManager.unwrap(Session.class);
    }
}
