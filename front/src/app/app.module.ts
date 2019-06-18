import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatIconModule } from '@angular/material/icon';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { MatDialogModule } from '@angular/material/dialog';

import { httpInterceptorProviders } from './auth/auth-interceptor';
import { AddImageComponent } from './add-image/add-image.component';
import { MyGalleryComponent } from './my-gallery/my-gallery.component';
import { NewPersonComponent } from './new-person/new-person.component';
import { TagService } from './services/tag.service';
import { PeopleService } from './services/people.service';
import { FileService } from './services/file.service';
import { ViewImageComponent } from './my-gallery/view-image/view-image.component';
import { EditImageComponent } from './my-gallery/edit-image/edit-image.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    RegisterComponent, 
    HomeComponent,
    AdminComponent,
    AddImageComponent,
    MyGalleryComponent,
    NewPersonComponent,
    ViewImageComponent,
    EditImageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgSelectModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    MatDialogModule,
    MatTooltipModule,
    MatIconModule
  ],
  entryComponents: [
    ViewImageComponent,
    EditImageComponent
  ],
  providers: [httpInterceptorProviders,
    TagService,
    PeopleService,
    FileService],
  bootstrap: [AppComponent]
})
export class AppModule { }
