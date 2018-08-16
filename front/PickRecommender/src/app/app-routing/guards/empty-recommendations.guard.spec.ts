import { TestBed, async, inject } from '@angular/core/testing';

import { EmptyRecommendationsGuard } from './empty-recommendations.guard';

describe('EmptyRecommendationsGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EmptyRecommendationsGuard]
    });
  });

  it('should ...', inject([EmptyRecommendationsGuard], (guard: EmptyRecommendationsGuard) => {
    expect(guard).toBeTruthy();
  }));
});
