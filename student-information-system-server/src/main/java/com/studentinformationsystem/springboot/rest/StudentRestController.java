package com.studentinformationsystem.springboot.rest;

import com.studentinformationsystem.springboot.entity.Course;
import com.studentinformationsystem.springboot.entity.Student;
import com.studentinformationsystem.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentRestController {


    private StudentService studentService;

    private StudentModelAssembler assembler;

    private PagedResourcesAssembler<Student> pagedResourcesAssembler;

    @Autowired
    public StudentRestController(StudentService studentService, StudentModelAssembler assembler,
                                 PagedResourcesAssembler pagedResourcesAssembler) {
        this.studentService = studentService;
        this.assembler = assembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("/students")
    public ResponseEntity<PagedModel<EntityModel<Student>>> findAll(Pageable pageable) {

        Page<Student> students = studentService.getAllStudents(pageable);

        PagedModel<EntityModel<Student>> studentsCollection = pagedResourcesAssembler.toModel(students, assembler);

        return new ResponseEntity<>(studentsCollection, HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}")
    public EntityModel<Student> getStudent(@PathVariable int studentId) {

        Student tempStudent = studentService.findStudentById(studentId);

        return assembler.toModel(tempStudent);

    }

    @GetMapping("/students/{studentId}/courses")
    public CollectionModel<?> getAllStudentsCourses(@PathVariable int studentId) {

        Student tempStudent = studentService.findStudentById(studentId);

        List<EntityModel<Course>> courses = tempStudent.getCourses().stream()
                .map(course -> EntityModel.of(course,
                        linkTo(methodOn(StudentRestController.class)
                            .getAllStudentsCourses(studentId)).withSelfRel())).collect(Collectors.toList());

        return CollectionModel.of(courses,
                linkTo(methodOn(StudentRestController.class).findAll(Pageable.unpaged())).withSelfRel());
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody Student tempStudent) {

        studentService.createStudent(tempStudent);

        EntityModel<Student> entityModel = assembler.toModel(tempStudent);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/students/{studentId}")
    public ResponseEntity<?> updateStudent(@RequestBody Student tempStudent, @PathVariable int studentId) {

        studentService.updateStudent(tempStudent, studentId);

        tempStudent.setId(studentId);

        EntityModel<Student> entityModel = assembler.toModel(tempStudent);

        return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable int studentId) {

        studentService.deleteById(studentId);

        return ResponseEntity.noContent().build();
    }
}

