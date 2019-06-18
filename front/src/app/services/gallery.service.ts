import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { People } from '../model/People';
import { Tag } from '../model/Tag';

@Injectable({
  providedIn: 'root'
})
export class GalleryService {

  constructor(private http: HttpClient) { }

  getImages(username: string): Observable<any> {
    return this.http.get(`http://localhost:8080/image/get-all-images/${username}`);
  }

  updateImageInfo(image: string, people: People[], tags: Tag[]): Observable<any> {
    const data = {image: image, people: people, tags: tags};
    return this.http.post(`http://localhost:8080/image/update-image-info`, data);
  }

  getImagesByElements(username: string, person: People, tag: Tag): Observable<any> {
    return this.http.get(`http://localhost:8080/image/get-images-by-element/${username}/${person.name}/${tag.name}`);
  }
}
