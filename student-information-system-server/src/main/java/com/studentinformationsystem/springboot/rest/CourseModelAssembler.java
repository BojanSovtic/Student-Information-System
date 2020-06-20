package com.studentinformationsystem.springboot.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.studentinformationsystem.springboot.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;



@Component
public class CourseModelAssembler implements RepresentationModelAssembler<Course, EntityModel<Course>> {

    @Override
    public EntityModel<Course> toModel(Course course) {

        return EntityModel.of(course,
                linkTo(methodOn(CourseRestController.class).getCourse(course.getId())).withSelfRel(),
                linkTo(methodOn(CourseRestController.class).findAll(Pageable.unpaged())).withRel("courses"),
                linkTo(methodOn(CourseRestController.class).getAllStudents(course.getId())).withRel("students"));
    }
}
