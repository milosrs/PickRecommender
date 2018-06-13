import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChampionHolderComponent } from './champion-holder.component';

describe('ChampionHolderComponent', () => {
  let component: ChampionHolderComponent;
  let fixture: ComponentFixture<ChampionHolderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChampionHolderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChampionHolderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
