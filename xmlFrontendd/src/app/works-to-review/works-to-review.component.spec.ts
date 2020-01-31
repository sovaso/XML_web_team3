import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorksToReviewComponent } from './works-to-review.component';

describe('WorksToReviewComponent', () => {
  let component: WorksToReviewComponent;
  let fixture: ComponentFixture<WorksToReviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorksToReviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorksToReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
