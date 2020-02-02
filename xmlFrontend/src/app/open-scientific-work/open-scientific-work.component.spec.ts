import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenScientificWorkComponent } from './open-scientific-work.component';

describe('OpenScientificWorkComponent', () => {
  let component: OpenScientificWorkComponent;
  let fixture: ComponentFixture<OpenScientificWorkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpenScientificWorkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpenScientificWorkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
