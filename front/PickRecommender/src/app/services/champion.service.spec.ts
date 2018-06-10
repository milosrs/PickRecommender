import { TestBed, inject } from '@angular/core/testing';

import { ChampionService } from './champion.service';

describe('ChampionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ChampionService]
    });
  });

  it('should be created', inject([ChampionService], (service: ChampionService) => {
    expect(service).toBeTruthy();
  }));
});
