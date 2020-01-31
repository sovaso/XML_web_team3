import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnreviewedWorksComponent } from './unreviewed-works.component';

describe('UnreviewedWorksComponent', () => {
  let component: UnreviewedWorksComponent;
  let fixture: ComponentFixture<UnreviewedWorksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnreviewedWorksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnreviewedWorksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
