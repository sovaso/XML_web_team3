import { TestBed } from '@angular/core/testing';

import { RegisterEditorService } from './register-editor.service';

describe('RegisterEditorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RegisterEditorService = TestBed.get(RegisterEditorService);
    expect(service).toBeTruthy();
  });
});
