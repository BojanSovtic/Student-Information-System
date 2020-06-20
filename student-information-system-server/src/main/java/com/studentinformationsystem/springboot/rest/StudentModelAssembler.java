package com.studentinformationsystem.springboot.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.studentinformationsystem.springboot.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class StudentModelAssembler implements RepresentationModelAssembler<Student, EntityModel<Student>> {

    @Override
    public EntityModel<Student> toModel(Student student) {

        return EntityModel.of(student,
                linkTo(methodOn(StudentRestController.class).getStudent(student.getId())).withSelfRel(),
                linkTo(methodOn(StudentRestController.class).findAll(Pageable.unpaged())).withRel("students"),
                linkTo(methodOn(StudentRestController.class).getAllStudentsCourses(student.getId())).withRel("courses"));
    }
}
