import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PagesComponent } from './pages/pages.component';
// import { ServicesComponent } from './services/services.component';
import { MenuBarComponent } from './menu-bar/menu-bar.component';
import { HeaderComponent } from './header/header.component';
import { AlertBoxComponent } from './alert-box/alert-box.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterUserComponent } from './pages/register-user/register-user.component';
import { ScientificWorksComponent } from './scientific-works/scientific-works.component';
import { MyWorksComponent } from './my-works/my-works.component';
import { WorksToReviewComponent } from './works-to-review/works-to-review.component';
import { UnreviewedWorksComponent } from './unreviewed-works/unreviewed-works.component';
import { ReviewedWorksComponent } from './reviewed-works/reviewed-works.component';
import { EditorComponent } from './editor/editor.component';
import { RegisterEditorComponent } from './register-editor/register-editor.component';
import { RegisterReviewerComponent } from './register-reviewer/register-reviewer.component';

@NgModule({
  declarations: [
    AppComponent,
    PagesComponent,
    //ServicesComponent,
    MenuBarComponent,
    HeaderComponent,
    AlertBoxComponent,
    DashboardComponent,
    LoginComponent,
    RegisterUserComponent,
    ScientificWorksComponent,
    MyWorksComponent,
    WorksToReviewComponent,
    UnreviewedWorksComponent,
    ReviewedWorksComponent,
    EditorComponent,
    RegisterEditorComponent,
    RegisterReviewerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
