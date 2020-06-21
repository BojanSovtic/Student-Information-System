import { Injectable } from '@angular/core';
import { Course } from '../models/course';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private courseUrl = 'http://localhost:8080/api/courses';

  constructor(private httpClient: HttpClient) { }
  
  getCourses(): Observable<Course[]> {

    return this.httpClient.get<GetResponseCourse>(this.courseUrl).pipe(
      map(response => response._embedded.courseList)
    )
  }
}

interface GetResponseCourse {
  _embedded: {
    courseList: Course[];
  }
}
