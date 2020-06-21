import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  students: Student[] = [];
  currentCourseId: number;
  currentCourseTitle: string;
  searchMode: boolean;

  thePageNumber: number = 1;
  thePageSize: number = 5;
  theTotalElements: number = 0;

  constructor(private studentService: StudentService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listStudents();
    });
  }

  handleListStudents() {

    const hasCourseId: boolean = this.route.snapshot.paramMap.has('id');

    if (hasCourseId) {
      this.currentCourseId = +this.route.snapshot.paramMap.get('id');
      this.currentCourseTitle = this.route.snapshot.paramMap.get('name');
    } else {
      this.currentCourseId = 1;
      this.currentCourseTitle = 'Algorithms and Data structures';
    }

    this.studentService.getStudentList(this.currentCourseId).subscribe(
      data => {
        this.students = data;
      }
    )
  }

  listAllStudents() {
    
    this.currentCourseTitle = '';

    this.studentService.getStudentListPaginate(this.thePageNumber - 1,
                                               this.thePageSize).subscribe(this.processResult());
  }

  handleSearchStudents() {

    const theKeyword: string = this.route.snapshot.paramMap.get('keyword');

    this.studentService.searchStudent(theKeyword).subscribe(
      data => {
        this.students = data;
      }
    );
  }

  listStudents() {
    
    this.searchMode = this.route.snapshot.paramMap.has('keyword');

    if (this.searchMode) {
      this.handleSearchStudents();
    } else {
      this.handleListStudents();
    }
  }

  processResult() {
    return data => {
      this.students = data._embedded.studentList;
      this.thePageNumber = data.page.number + 1;
      this.thePageSize = data.page.size;
      this.theTotalElements = data.page.totalElements;
    }
  }

  updatePageSize(thePageSize: number) {
    this.thePageSize = thePageSize;
    this.thePageNumber = 1;
    this.listStudents();
  }

}
