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
import { RegisterEditorComponent } from './register-editor/register-editor.component';
import { RegisterReviewerComponent } from './register-reviewer/register-reviewer.component';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtUtilsService } from './security/jwt-utils.service';
import { AuthenticationService } from './security/authentication.service';
import { TokenInterceptorService } from './security/token-interceptor.service';
import { CanActivateAuthGuard } from './security/can-acitvate-auth.guard';
import { AllScientificWorksComponent } from './all-scientific-works/all-scientific-works.component';
import { OpenScientificWorkComponent } from './open-scientific-work/open-scientific-work.component';
import { ForRevisionComponent } from './for-revision/for-revision.component';

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
    AlertBoxComponent,
    RegisterUserComponent,
    ScientificWorksComponent,
    MyWorksComponent,
    WorksToReviewComponent,
    UnreviewedWorksComponent,
    ReviewedWorksComponent,
    RegisterEditorComponent,
    RegisterReviewerComponent,
    AllScientificWorksComponent,
    OpenScientificWorkComponent,
    ForRevisionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [
    CanActivateAuthGuard,
    JwtUtilsService,
    AuthenticationService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    AlertBoxComponent
  ]
})
export class AppModule { }
