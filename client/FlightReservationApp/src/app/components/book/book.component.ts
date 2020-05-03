import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FlightService } from '../../service/flight.service';
import { Flights } from 'src/app/model/model.flight';


@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  //formgroup object
  bookForm: FormGroup;

  invalidLogin: boolean = false;
  isValidUser: boolean = false;

  submitted: boolean = false;
  flight: Flights;

  constructor(private formBuilder: FormBuilder, private router: Router,
    private flightService: FlightService) { }

  ngOnInit() {
    this.bookForm = this.formBuilder.group({
      flightid: ['', Validators.required],
      
    });

    this.flight = this.flightService.getBookFlight();
  }

  paynow() {
    alert(`Make Payment`);

    this.flightService.bookFlight(this.flight.flightId,sessionStorage.userid).subscribe(data => {
      console.log(data);
      alert(`Payment Successful`);
      this.router.navigate(['/home']);

    }, err => {
      console.log(err.stack())
    })
  }

}
