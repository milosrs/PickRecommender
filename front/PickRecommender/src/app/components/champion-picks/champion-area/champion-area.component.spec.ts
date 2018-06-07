import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChampionAreaComponent } from './champion-area.component';

describe('ChampionAreaComponent', () => {
  let component: ChampionAreaComponent;
  let fixture: ComponentFixture<ChampionAreaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChampionAreaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChampionAreaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
