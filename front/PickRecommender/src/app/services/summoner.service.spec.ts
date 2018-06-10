import { TestBed, inject } from '@angular/core/testing';

import { SummonerService } from './summoner.service';

describe('SummonerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SummonerService]
    });
  });

  it('should be created', inject([SummonerService], (service: SummonerService) => {
    expect(service).toBeTruthy();
  }));
});
