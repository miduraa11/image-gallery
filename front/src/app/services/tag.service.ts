import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TagService {

  constructor(private http: HttpClient) { }

  getAllTags(): Observable<any> {
    return this.http.get('http://localhost:8080/tag/get-all-tags');
  }

  addNewTag(name: string): Observable<any> {
    return this.http.post(`http://localhost:8080/tag/add-new-tag`, name);
  }

  getImageTags(image: string): Observable<any> {
    return this.http.get(`http://localhost:8080/tag/get-image-tags/${image}`);
  }
}
