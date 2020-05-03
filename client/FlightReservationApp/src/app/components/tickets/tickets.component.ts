import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FlightService } from '../../service/flight.service';
import { Flights } from '../../model/model.flight';
import { Tickets } from 'src/app/model/model.ticket';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {
  tickets: Tickets[]=[];
  constructor(private formBuilder: FormBuilder, private router: Router,
    private flightService :FlightService) { }

  ngOnInit(): void {
    this.flightService.viewTickets(sessionStorage.userid).subscribe(data=>{
      this.tickets = data;
      console.log(this.tickets);
    })
  }

  // Cancellation of ticket
  cancel(bookId:number){
    console.log(bookId)
    alert(`Your Cancellation receipt is downloaded`);

    
    this.flightService.cancelTicket(bookId).subscribe(data=>{
      console.log(data);
      alert(`Reservation cancelled`);
      this.flightService.viewTickets(sessionStorage.userid).subscribe(data=>{
        this.tickets = data;
        console.log(this.tickets);
      })
    })
  }
}
