package com.studentinformationsystem.springboot.rest;

import com.studentinformationsystem.springboot.entity.Course;
import com.studentinformationsystem.springboot.entity.Professor;
import com.studentinformationsystem.springboot.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProfessorRestController {

    private ProfessorService professorService;

    private ProfessorModelAssembler assembler;

    @Autowired
    public ProfessorRestController(ProfessorService professorService, ProfessorModelAssembler assembler) {
        this.professorService = professorService;
        this.assembler = assembler;
    }

    @GetMapping("/professors")
    public CollectionModel<EntityModel<Professor>> findAll() {

        List<EntityModel<Professor>> professors = professorService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(professors,
                    linkTo(methodOn(ProfessorRestController.class).findAll()).withSelfRel());
     }

    @GetMapping("/professors/{professorId}")
    public EntityModel<Professor> getProfessor(@PathVariable int professorId) {

        Professor tempProfessor = professorService.findById(professorId);

        if (tempProfessor == null) {
            throw new ProfessorNotFoundException(professorId);
        }

        return assembler.toModel(tempProfessor);
    }

    @GetMapping("/professors/{professorId}/courses")
    public CollectionModel<EntityModel<Course>> getProfessorsCourses(@PathVariable int professorId) {

        Professor tempProfessor = professorService.findById(professorId);

        if (tempProfessor == null) {
            throw new ProfessorNotFoundException(professorId);
        }

        List<EntityModel<Course>> courses = tempProfessor.getCourses().stream()
                .map(course -> EntityModel.of(course,
                        linkTo(methodOn(ProfessorRestController.class)
                                .getProfessorsCourses(professorId)).withSelfRel())).collect(Collectors.toList());

        return CollectionModel.of(courses,
                linkTo(methodOn(ProfessorRestController.class).findAll()).withSelfRel());
    }

    @PostMapping("/professors")
    public ResponseEntity<?> addProfessor(@RequestBody Professor tempProfessor) {

        // to force a save instead of update
        tempProfessor.setId(0);

        EntityModel<Professor> entityModel = assembler.toModel(professorService.save(tempProfessor));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/professors")
    public ResponseEntity<?> updateProfessor(@RequestBody Professor tempProfessor) {

        professorService.save(tempProfessor);

        EntityModel<Professor> entityModel = assembler.toModel(tempProfessor);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/professors/{professorId}")
    public ResponseEntity<?> deleteProfessor(@PathVariable int professorId) {

        Professor tempProfessor = professorService.findById(professorId);

        if (tempProfessor == null) {
            throw new ProfessorNotFoundException(professorId);
        }

        professorService.deleteById(professorId);

        return ResponseEntity.noContent().build();
    }

}
