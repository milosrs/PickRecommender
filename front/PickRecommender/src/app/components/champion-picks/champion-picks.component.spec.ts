import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChampionPicksComponent } from './champion-picks.component';

describe('ChampionPicksComponent', () => {
  let component: ChampionPicksComponent;
  let fixture: ComponentFixture<ChampionPicksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChampionPicksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChampionPicksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
