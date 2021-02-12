import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FamilyComponent } from './components/family/family.component';
import { LoginComponent } from './components/login/login.component';
import { AddUserComponent } from './components/add-user/add-user.component';
import { AddPostComponent } from './components/add-post/add-post.component';
import { FamilytreeComponent } from './components/familytree/familytree.component';
import { AddReviewComponent } from './components/add-review/add-review.component';
import { CareerAdviseComponent } from './components/career-advise/career-advise.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: LoginComponent,
      },
      {
        path: 'dashboard',
        component: FamilyComponent,
      },
      {
        path: 'familyTree',
        component: FamilytreeComponent,
      },
      {
        path: 'addUser',
        component: AddUserComponent,
      },
      {
        path: 'addPost',
        component: AddPostComponent,
      },
      {
        path: 'addReview',
        component: AddReviewComponent,
      },
      {
        path: 'career',
        component: CareerAdviseComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
