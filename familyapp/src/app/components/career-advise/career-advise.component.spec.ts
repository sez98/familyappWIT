import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CareerAdviseComponent } from './career-advise.component';

describe('CareerAdviseComponent', () => {
  let component: CareerAdviseComponent;
  let fixture: ComponentFixture<CareerAdviseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CareerAdviseComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CareerAdviseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
