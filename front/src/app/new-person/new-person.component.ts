import { Component, OnInit } from '@angular/core';
import { People } from '../model/People';
import { PeopleService } from '../services/people.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-new-person',
  templateUrl: './new-person.component.html',
  styleUrls: ['./new-person.component.css']
})

export class NewPersonComponent implements OnInit {

  personForm = new FormGroup({
    name: new FormControl('', [
      Validators.required
    ])
});

  constructor(private peopleService: PeopleService,
    private tokenStorage: TokenStorageService,
    public snackBar: MatSnackBar) { }

  people: People[];

  ngOnInit() {
    this.loadAllUserPeople();
    }

    loadAllUserPeople(){
      let username = this.tokenStorage.getUsername();
      this.peopleService.getAllUserPeople(username).subscribe(data => this.people = data);
    }

    addPerson(){
      if(this.personForm.valid){
        let username = this.tokenStorage.getUsername();
        let person = this.personForm.get('name').value;
        this.peopleService.addNewPerson(username, person).subscribe( data => {
          this.loadAllUserPeople();
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
