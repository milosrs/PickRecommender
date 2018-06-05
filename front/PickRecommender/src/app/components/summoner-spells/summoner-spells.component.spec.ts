import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SummonerSpellsComponent } from './summoner-spells.component';

describe('SummonerSpellsComponent', () => {
  let component: SummonerSpellsComponent;
  let fixture: ComponentFixture<SummonerSpellsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SummonerSpellsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SummonerSpellsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
