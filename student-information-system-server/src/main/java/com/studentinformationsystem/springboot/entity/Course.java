package com.studentinformationsystem.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="course_id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="semester")
    private String semester;

    @Column(name="year")
    private Integer year;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER,
               cascade={CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="professor_id")
    private Professor professor;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY,
                cascade={CascadeType.PERSIST, CascadeType.MERGE,
                         CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="student_course",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="student_id")
    )
    private List<Student> students;

    public Course() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student tempStudent) {

        if (students == null) {
            students = new ArrayList<>();
        }

        students.add(tempStudent);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", semester='" + semester + '\'' +
                ", year=" + year +
                ", professor=" + professor +
                ", students=" + students +
                '}';
    }
}
