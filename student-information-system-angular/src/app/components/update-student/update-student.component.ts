import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { StudentService } from 'src/app/services/student.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Student } from 'src/app/models/student';

@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.css']
})
export class UpdateStudentComponent implements OnInit {

  student: Student = new Student();
  studentId: number;

  updateStudentFormGroup: FormGroup;

  constructor(private formBuilder: FormBuilder, private studentService: StudentService, 
              private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.route.paramMap.subscribe(() => {
      this.getStudentAndUpdateForm();
    });
    
    this.updateStudentFormGroup = this.formBuilder.group({
      student: this.formBuilder.group({
        firstName: [],
        lastName: [],
        email: [],
        indexNumber: []
      })
    });
    
  }


  getStudentAndUpdateForm() {
    
    this.studentId = +this.route.snapshot.paramMap.get('id'); 

    this.studentService.getStudent(this.studentId).subscribe(
      data => {
        this.student = data;
        this.updateStudentFormGroup.patchValue({
          student: {
          firstName: this.student.firstName,
          lastName: this.student.lastName,
           email: this.student.email,
          indexNumber: this.student.indexNumber
         }
       });
      }
    );
  }

  onSubmit() {
    this.student = this.updateStudentFormGroup.get('student').value;
    this.student.id = this.studentId;
    this.studentService.updateStudent(this.student).subscribe(
      res => {
        console.log(res);
        this.router.navigate([`/students/update/${this.student.id}`]);
      }
    )
  }

}
