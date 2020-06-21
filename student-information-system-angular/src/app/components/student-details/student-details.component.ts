import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {

  student: Student = new Student();

  constructor(private studentService: StudentService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.handleStudentDetails();
    });
  }

  handleStudentDetails() {

    const theStudentId: number = +this.route.snapshot.paramMap.get('id');

    this.studentService.getStudent(theStudentId).subscribe(
      data => {
        this.student = data;
      }
    );
  }
  
  deleteStudent() {
    
    this.studentService.deleteStudent(this.student).subscribe(
      res => {
        console.log(res);
        this.router.navigate([`/students`]);
      }
    )
  }

}
