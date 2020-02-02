import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterUserComponent } from './pages/register-user/register-user.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ScientificWorksComponent } from './scientific-works/scientific-works.component';
import { MyWorksComponent } from './my-works/my-works.component';
import { WorksToReviewComponent } from './works-to-review/works-to-review.component';
import { UnreviewedWorksComponent } from './unreviewed-works/unreviewed-works.component';
import { ReviewedWorksComponent } from './reviewed-works/reviewed-works.component';
import { RegisterEditorComponent } from './register-editor/register-editor.component';
import { RegisterReviewerComponent } from './register-reviewer/register-reviewer.component';
import { AllScientificWorksComponent } from './all-scientific-works/all-scientific-works.component';




const routes: Routes = [
 {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'registerUser',
    component: RegisterUserComponent,
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      { path: 'allScientificWorks', component: AllScientificWorksComponent},
      { path: 'scientificWorks', component: ScientificWorksComponent},
      { path: 'myWorks', component: MyWorksComponent},
      { path: 'worksToReview', component: WorksToReviewComponent},
      { path : 'unreviewedWorks' , component : UnreviewedWorksComponent},
      { path : 'reviewedWorks', component : ReviewedWorksComponent},
      { path : 'registerEditor', component : RegisterEditorComponent},
      { path : 'registerReviewer', component : RegisterReviewerComponent},
    ] 
  },

  {path: '**', redirectTo: 'dashboard'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }