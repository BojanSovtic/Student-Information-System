import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { Routes, RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { StudentService } from './services/student.service';
import { StudentListComponent } from './components/student-list/student-list.component';
import { CoursesMenuComponent } from './components/courses-menu/courses-menu.component';
import { SearchComponent } from './components/search/search.component';
import { StudentDetailsComponent } from './components/student-details/student-details.component';
import { CreateStudentComponent } from './components/create-student/create-student.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UpdateStudentComponent } from './components/update-student/update-student.component';

const routes: Routes = [
  {path: 'search/:keyword', component: StudentListComponent},
  {path: 'courses/:id/:name', component: StudentListComponent},
  {path: 'courses', component: StudentListComponent},
  {path: 'students/update/:id', component: UpdateStudentComponent},
  {path: 'students/new', component: CreateStudentComponent},
  {path: 'students/:id', component: StudentDetailsComponent},
  {path: 'students', component: StudentListComponent},
  {path: '', redirectTo: '/students', pathMatch: 'full'},
  {path: '**', redirectTo: '/students', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    StudentListComponent,
    CoursesMenuComponent,
    SearchComponent,
    StudentDetailsComponent,
    CreateStudentComponent,
    UpdateStudentComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule
  ],
  providers: [StudentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
