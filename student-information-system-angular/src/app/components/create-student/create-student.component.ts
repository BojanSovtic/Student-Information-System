import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { StudentService } from 'src/app/services/student.service';
import { Student } from 'src/app/models/student';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-student',
  templateUrl: './create-student.component.html',
  styleUrls: ['./create-student.component.css']
})
export class CreateStudentComponent implements OnInit {
  
  student: Student = new Student();

  createStudentFormGroup: FormGroup;

  constructor(private formBuilder: FormBuilder, private studentService: StudentService, private router: Router) { }

  ngOnInit(): void {

    this.createStudentFormGroup = this.formBuilder.group({
      student: this.formBuilder.group({
        firstName: [],
        lastName: [],
        email: [],
        indexNumber: []
      })
    });
  }

  onSubmit() {
    console.log(this.createStudentFormGroup.get('student').value);
    this.student = this.createStudentFormGroup.get('student').value;
    this.studentService.saveStudent(this.student).subscribe(
      res => {
        console.log(res);
        this.router.navigate(['/students']);
      }
    )
  }
}
