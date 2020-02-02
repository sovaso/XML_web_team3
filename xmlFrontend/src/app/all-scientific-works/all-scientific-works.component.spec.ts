import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllScientificWorksComponent } from './all-scientific-works.component';

describe('AllScientificWorksComponent', () => {
  let component: AllScientificWorksComponent;
  let fixture: ComponentFixture<AllScientificWorksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllScientificWorksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllScientificWorksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
