import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  constructor(private http: HttpClient) { }

  getAllUserPeople(username: string): Observable<any>{
    return this.http.get(`http://localhost:8080/people/get-all-people/${username}`)
  }

  addNewPerson(username: string, person: string): Observable<any>{
    const data = {username: username, person: person};
    return this.http.post('http://localhost:8080/people/add-new-person', data)
  }

  getAllImagePeople(image: string): Observable<any> {
    return this.http.get(`http://localhost:8080/people/get-all-people-by-image/${image}`)
  }
}
