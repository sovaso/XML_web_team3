import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewedWorksComponent } from './reviewed-works.component';

describe('ReviewedWorksComponent', () => {
  let component: ReviewedWorksComponent;
  let fixture: ComponentFixture<ReviewedWorksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewedWorksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewedWorksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
