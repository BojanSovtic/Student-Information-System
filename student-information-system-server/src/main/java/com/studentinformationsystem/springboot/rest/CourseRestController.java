package com.studentinformationsystem.springboot.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.studentinformationsystem.springboot.entity.Course;
import com.studentinformationsystem.springboot.entity.Professor;
import com.studentinformationsystem.springboot.entity.Student;
import com.studentinformationsystem.springboot.service.CourseService;
import com.studentinformationsystem.springboot.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CourseRestController {

    private CourseService courseService;
    private ProfessorService professorService;

    private CourseModelAssembler assembler;

    private PagedResourcesAssembler<Course> pagedResourcesAssembler;

    @Autowired
    public CourseRestController(CourseService courseService, CourseModelAssembler courseModelAssembler,
                                PagedResourcesAssembler pagedResourcesAssembler, ProfessorService professorService) {
        this.courseService = courseService;
        this.assembler = courseModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.professorService = professorService;
    }

    @GetMapping("/courses")
    public ResponseEntity<?> findAll(Pageable pageable) {

        Page<Course> courses = courseService.getAllCourses(pageable);

        PagedModel<EntityModel<Course>> coursesCollection = pagedResourcesAssembler.toModel(courses, assembler);

        return new ResponseEntity<>(coursesCollection, HttpStatus.OK);
    }

    @GetMapping("/courses/{courseId}")
    public EntityModel<Course> getCourse(@PathVariable int courseId) {

        Course tempCourse = courseService.findCourseById(courseId);

        return assembler.toModel(tempCourse);
    }

    @GetMapping("/courses/{courseId}/students")
    public CollectionModel<?> getAllStudents(@PathVariable int courseId) {

        Course tempCourse = courseService.findCourseById(courseId);

        List<EntityModel<Student>> students = tempCourse.getStudents().stream()
                .map(student -> EntityModel.of(student,
                        linkTo(methodOn(CourseRestController.class)
                            .getAllStudents(courseId)).withSelfRel())).collect(Collectors.toList());

        return CollectionModel.of(students,
                linkTo(methodOn(CourseRestController.class).findAll(Pageable.unpaged())).withSelfRel());
    }

    @PostMapping("/courses/{professorId}")
    public ResponseEntity<?> addCourse(@RequestBody Course tempCourse, @PathVariable int professorId) {

        Professor tempProfessor = professorService.findById(professorId);

        tempCourse.setProfessor(tempProfessor);

        courseService.createCourse(tempCourse);

        EntityModel<Course> entityModel = assembler.toModel(tempCourse);

        return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
    }

    @PutMapping("/courses/{courseId}/professor/{professorId}")
    public ResponseEntity<?> updateCourse(@RequestBody Course tempCourse, @PathVariable int courseId,
                                          @PathVariable int professorId) {

        Professor tempProfessor = professorService.findById(professorId);
        tempCourse.setProfessor(tempProfessor);

        courseService.updateCourse(tempCourse, courseId);

        tempCourse.setId(courseId);

        EntityModel<Course> entityModel = assembler.toModel(tempCourse);

        return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId) {

        courseService.deleteById(courseId);

        return ResponseEntity.noContent().build();
    }


}
