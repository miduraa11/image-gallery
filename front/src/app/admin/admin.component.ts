import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Tag } from '../model/Tag';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TagService } from '../services/tag.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  board: string;
  errorMessage: string;


  tagForm = new FormGroup({
    name: new FormControl('', [
      Validators.required
    ])
});

  constructor(private userService: UserService,
    public snackBar: MatSnackBar,
    private tagService: TagService) { }

  tags: Tag[];

  ngOnInit() {
    this.laodAllTags();
  }

  laodAllTags(){
    this.tagService.getAllTags().subscribe(data => this.tags = data)
  }

  addTag(){
    if(this.tagForm.valid){
      this.tagService.addNewTag(this.tagForm.get('name').value).subscribe( data => {
        this.laodAllTags();
        this.openSuccessSnackBar();
      })
    } else this.openErrorSnackBar();
    
  }

  openErrorSnackBar() {
    this.snackBar.open('Błąd ! Nie dodano', 'Zamknij', {
      duration: 6000,
      verticalPosition: 'top',
    });
  }

  openSuccessSnackBar() {
    this.snackBar.open('Pomyślnie dodano', 'Zamknij', {
      duration: 6000,
      verticalPosition: 'top',
    });
  }
}
