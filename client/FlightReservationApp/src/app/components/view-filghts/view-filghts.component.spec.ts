import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFilghtsComponent } from './view-filghts.component';

describe('ViewFilghtsComponent', () => {
  let component: ViewFilghtsComponent;
  let fixture: ComponentFixture<ViewFilghtsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewFilghtsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFilghtsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
