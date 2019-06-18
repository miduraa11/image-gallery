import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TokenStorageService } from '../auth/token-storage.service';
import { UploadFile } from '../model/UploadFile';
import { TagService } from '../services/tag.service';
import { PeopleService } from '../services/people.service';
import { FileService } from '../services/file.service';
import { People } from '../model/People';
import { Tag } from '../model/Tag';

@Component({
  selector: 'app-add-image',
  templateUrl: './add-image.component.html',
  styleUrls: ['./add-image.component.css']
})
export class AddImageComponent implements OnInit {

  imageForm = new FormGroup({
    image: new FormControl('', [
      Validators.required,
    ]),
    people: new FormControl('', []),
    tags: new FormControl('', []),
});

  constructor(public snackBar: MatSnackBar,
    private tagService: TagService,
    private peopleService: PeopleService,
    private fileService: FileService,
    private tokenStorage: TokenStorageService) { }

  selectedImage = null;
  newFileInformation = new UploadFile();
  people: People[];
  tags: Tag[];
  token: any;
  imageUrl: any;

  ngOnInit() {
    this.loadUserPeople();
    this.loadAllTags();
    this.token = this.tokenStorage.getToken();
  }

  onFileSelected(event){
    this.selectedImage = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(this.selectedImage);
    reader.onload = (event) => {
      this.imageUrl = reader.result;
    }

  }

  onUpload(){
    if(this.imageForm.valid){
      let username = this.tokenStorage.getUsername();
      this.newFileInformation.username = username;
      const formData = new FormData();
      formData.append('image', this.selectedImage);
      formData.append('username', username);
      this.newFileInformation.people = this.imageForm.get('people').value;
      this.newFileInformation.tags = this.imageForm.get('tags').value;
      console.log(this.imageForm.get('tags').value);
      this.fileService.uploadImage(formData).subscribe(data => {
        console.log(data);
        if(data!=0 && data!=-1){
          this.newFileInformation.imageId = data;
          this.fileService.setImageInformation(this.newFileInformation).subscribe( data => console.log(data));
          this.openImageAddedSnackBar();
        } else if(data==0) this.openErrorUploadSnackBar();
        else this.openErrorFileNameSnackBar();
      });
    } else this.openErrorFormSnackBar();
  }

  loadUserPeople(){
    let username = this.tokenStorage.getUsername();
    this.peopleService.getAllUserPeople(username).subscribe(data => this.people = data);
  }

  loadAllTags(){
    this.tagService.getAllTags().subscribe(data => this.tags = data);
  }

  openErrorFormSnackBar() {
    this.snackBar.open('Nie wybrano zdjęcia !', 'Zamknij', {
      duration: 6000,
      verticalPosition: 'top',
    });
  }

  openErrorUploadSnackBar() {
    this.snackBar.open('Błąd ! Nie zapisano zdjęcia !', 'Zamknij', {
      duration: 6000,
      verticalPosition: 'top',
    });
  }

  openErrorFileNameSnackBar() {
    this.snackBar.open('Zdjęcie o podanej nazwie już istnieje !', 'Zamknij', {
      duration: 6000,
      verticalPosition: 'top',
    });
  }

  openImageAddedSnackBar() {
    this.snackBar.open('Pomyślnie dodano zdjęcie', 'Zamknij', {
      duration: 6000,
      verticalPosition: 'top',
    });
  }

}
