import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayerPositionComponent } from './player-position.component';

describe('PlayerPositionComponent', () => {
  let component: PlayerPositionComponent;
  let fixture: ComponentFixture<PlayerPositionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlayerPositionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayerPositionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
