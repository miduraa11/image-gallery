import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PeopleService } from '../../services/people.service';

@Component({
  selector: 'app-view-image',
  templateUrl: './view-image.component.html',
  styleUrls: ['./view-image.component.css']
})
export class ViewImageComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ViewImageComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private peopleService: PeopleService) { }

    people: string = '';

  ngOnInit() {
    this.loadImagePeople();
  }

  loadImagePeople(){
    this.peopleService.getAllImagePeople(this.data).subscribe(data => {
      this.fillterPeople(data);
    });
  }

  fillterPeople(data: any){
    for (let entry of data) {
      this.people = this.people + " " + entry.name;
    }
  }

}
