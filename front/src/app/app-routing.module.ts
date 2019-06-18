import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { AddImageComponent } from './add-image/add-image.component';
import { MyGalleryComponent } from './my-gallery/my-gallery.component';
import { NewPersonComponent } from './new-person/new-person.component';

const routes: Routes = [
    {
        path: 'home',
        component: HomeComponent
    },
    {
        path: 'user',
        component: UserComponent
    },
    {
        path: 'admin',
        component: AdminComponent
    },
    {
        path: 'auth/login',
        component: LoginComponent
    },
    {
        path: 'signup',
        component: RegisterComponent
    },
    {
        path: 'add-image',
        component: AddImageComponent
    },
    {
        path: 'my-gallery',
        component: MyGalleryComponent
    },
    {
        path: 'new-person',
        component: NewPersonComponent
    },
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
