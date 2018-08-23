import { TestBed, inject } from '@angular/core/testing';

import { RunesService } from './runes.service';

describe('RunesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RunesService]
    });
  });

  it('should be created', inject([RunesService], (service: RunesService) => {
    expect(service).toBeTruthy();
  }));
});
