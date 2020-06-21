import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student } from '../models/student';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  
  private baseUrl = 'http://localhost:8080/api/students';

  private coursesUrl = 'http://localhost:8080/api/courses';

  constructor(private httpClient: HttpClient) { }

  getStudentList(theCourseId: number): Observable<Student[]> {

    const url = `${this.coursesUrl}/${theCourseId}/students`;

    return this.httpClient.get<GetResponseStudent>(url).pipe(
      map(response => response._embedded.studentList)
    );
  }

  searchStudent(theKeyword: string): Observable<Student[]> {
    
    const searchUrl = `${this.baseUrl}/search/findByEmailContaining/${theKeyword}`;

    return this.httpClient.get<GetResponseStudent>(searchUrl).pipe(
      map(response => { 
        if (response._embedded) {
            return response._embedded.studentList
          } 
      })
    );
  }

  getStudent(theStudentId: number): Observable<Student> {

    const studentUrl = `${this.baseUrl}/${theStudentId}`;

    return this.httpClient.get<Student>(studentUrl);
  }

  getStudentListPaginate(thePage: number,
                         thePageSize: number): Observable<GetResponseStudent> {
      
      const url = `${this.baseUrl}?page=${thePage}&size=${thePageSize}`;

      return this.httpClient.get<GetResponseStudent>(url);
  }

  saveStudent(student: Student): Observable<Student> {

    return this.httpClient.post<Student>(this.baseUrl, student).pipe(
      map(res => {
        return res;
      })
    );
  }

  updateStudent(student: Student): Observable<Student> {

    const url = `${this.baseUrl}/${student.id}`;

    return this.httpClient.put<Student>(url, student).pipe(
      map(res => {
        return res;
      })
    );
  }

  deleteStudent(student: Student): Observable<Student> {

    const url = `${this.baseUrl}/${student.id}`;

    return this.httpClient.delete<Student>(url).pipe(
      map(res => {
        return res;
      })
    );
  }

}

interface GetResponseStudent {
  _embedded: {
    studentList: Student[];
  },
  page: {
    size: number,
    totalElements: number,
    totalPages: number,
    number: number
  }
}
