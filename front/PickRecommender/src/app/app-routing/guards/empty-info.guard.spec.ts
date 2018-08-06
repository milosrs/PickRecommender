import { TestBed, async, inject } from '@angular/core/testing';

import { EmptyInfoGuard } from './empty-info.guard';

describe('EmptyInfoGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EmptyInfoGuard]
    });
  });

  it('should ...', inject([EmptyInfoGuard], (guard: EmptyInfoGuard) => {
    expect(guard).toBeTruthy();
  }));
});
