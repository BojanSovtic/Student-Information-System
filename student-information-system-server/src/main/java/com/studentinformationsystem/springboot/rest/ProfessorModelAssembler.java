package com.studentinformationsystem.springboot.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.studentinformationsystem.springboot.entity.Professor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProfessorModelAssembler implements RepresentationModelAssembler<Professor, EntityModel<Professor>> {

    @Override
    public EntityModel<Professor> toModel(Professor professor) {

        return EntityModel.of(professor,
                linkTo(methodOn(ProfessorRestController.class).getProfessor(professor.getId())).withSelfRel(),
                linkTo(methodOn(ProfessorRestController.class).findAll()).withRel("professors"),
                linkTo(methodOn(ProfessorRestController.class).getProfessorsCourses(professor.getId())).withRel("courses"));
    }
}
