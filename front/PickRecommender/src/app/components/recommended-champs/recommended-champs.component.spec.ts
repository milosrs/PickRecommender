import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommendedChampsComponent } from './recommended-champs.component';

describe('RecommendedChampsComponent', () => {
  let component: RecommendedChampsComponent;
  let fixture: ComponentFixture<RecommendedChampsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecommendedChampsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommendedChampsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
