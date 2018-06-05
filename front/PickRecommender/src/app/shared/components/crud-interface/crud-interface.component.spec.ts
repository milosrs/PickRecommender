import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudInterfaceComponent } from './crud-interface.component';

describe('CrudInterfaceComponent', () => {
  let component: CrudInterfaceComponent;
  let fixture: ComponentFixture<CrudInterfaceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrudInterfaceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrudInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
