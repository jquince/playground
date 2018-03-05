import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApiInOutCardComponent } from './api-in-out-card.component';

describe('ApiInOutCardComponent', () => {
  let component: ApiInOutCardComponent;
  let fixture: ComponentFixture<ApiInOutCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApiInOutCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApiInOutCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
