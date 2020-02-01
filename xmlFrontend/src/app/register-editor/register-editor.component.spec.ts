import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterEditorComponent } from './register-editor.component';

describe('RegisterEditorComponent', () => {
  let component: RegisterEditorComponent;
  let fixture: ComponentFixture<RegisterEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
