import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UploadFile } from '../model/UploadFile';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private http: HttpClient) { }

  setImageInformation(file: UploadFile):Observable<any> {
    return this.http.post('http://localhost:8080/image/set-image-info', file);
  }

  uploadImage(formData: FormData):Observable<any> {
    return this.http.post('http://localhost:8080/image/add-image', formData);
  }
  
}
