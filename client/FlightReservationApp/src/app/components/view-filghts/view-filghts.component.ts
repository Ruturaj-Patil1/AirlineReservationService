import { Component, OnInit } from '@angular/core';
import {NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FlightService } from '../../service/flight.service';
import { Flights } from '../../model/model.flight';



@Component({
  selector: 'app-view-filghts',
  templateUrl: './view-filghts.component.html',
  styleUrls: ['./view-filghts.component.css']
})
export class ViewFilghtsComponent implements OnInit {
  source:any="X";
  destination:any="Y";
  submitted: boolean= false;
  searchForm : FormGroup;
  model: NgbDateStruct;
  // flights1:Flight[]=[];
  // Airline : Flight1[]=[];

  flights:Flights[]=[];
  constructor(private formBuilder: FormBuilder, private router: Router,
    private flightService :FlightService) { }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      source:['',Validators.required],
      destination: ['', Validators.required],
    });
  }

  searchFlights(){
    console.log(this.searchForm.value);

    this.source = this.searchForm.value.source;
    this.destination = this.searchForm.value.destination;
    this.submitted = true;
    this.flightService.getFlight(this.source,this.destination).subscribe(data=>{
      this.flights = data;
      console.log(this.flights);
    })
  }

  bookFlight(selectedFlight:any){

    this.flightService.setBookFlight(selectedFlight);
    console.log(selectedFlight);
    this.router.navigate(['/book']);
  }
}
