import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormControl } from '@angular/forms';
import { People } from '../../model/People';
import { Tag } from '../../model/Tag';
import { TagService } from '../../services/tag.service';
import { PeopleService } from '../../services/people.service';
import { TokenStorageService } from '../../auth/token-storage.service';
import { GalleryService } from '../../services/gallery.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-edit-image',
  templateUrl: './edit-image.component.html',
  styleUrls: ['./edit-image.component.css']
})
export class EditImageComponent implements OnInit {

  editImageForm = new FormGroup({
    people: new FormControl('', []),
    tags: new FormControl('', []),
});

  constructor(public dialogRef: MatDialogRef<EditImageComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private tagService: TagService,
    private peopleService: PeopleService,
    private tokenStorage: TokenStorageService,
    private galleryService: GalleryService,
    public snackBar: MatSnackBar) { }

    people: People[];
    tags: Tag[];
    selectedPeople: any;
    selectedTags: any;

  ngOnInit() {
    this.loadUserPeople();
    this.loadAllTags();
    this.loadImageTags();
    this.loadImagePeople();
  }

  loadImageTags(){
    this.tagService.getImageTags(this.data).subscribe(data => this.selectedTags = data)
  }

  loadImagePeople(){
    this.peopleService.getAllImagePeople(this.data).subscribe(data => this.selectedPeople = data)
  }

  save(){
    this.people = this.editImageForm.get('people').value;
    this.tags = this.editImageForm.get('tags').value;
    this.galleryService.updateImageInfo(this.data, this.people, this.tags).subscribe(data => {
      if(data==1){
        this.openSuccessSnackBar();
        this.dialogRef.close();
      }
    })
  }

  loadUserPeople(){
    let username = this.tokenStorage.getUsername();
    this.peopleService.getAllUserPeople(username).subscribe(data => this.people = data);
  }

  loadAllTags(){
    this.tagService.getAllTags().subscribe(data => this.tags = data);
  }

  openSuccessSnackBar() {
    this.snackBar.open('Pomyślnie dodano', 'Zamknij', {
      duration: 6000,
      verticalPosition: 'top',
    });
  }

}
