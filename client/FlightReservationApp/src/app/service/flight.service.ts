import { Injectable } from '@angular/core';
import { Flights } from '../model/model.flight';
import { HttpClient } from '@angular/common/http';
import { Users } from '../model/model.user';
import { Tickets } from '../model/model.ticket';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  baseUrl: string = "http://localhost:8098";
  static bookflight :Flights;
 

  constructor(private http: HttpClient) { }

  setBookFlight(flight :Flights){
   FlightService.bookflight = flight;
  }

  //Get all flight details
  getBookFlight(){
    return FlightService.bookflight;
  }


  //book flight
  bookFlight(flightid:number,userId:number){
    return this.http.get<Flights[]>(`${this.baseUrl}/bookFlight/${flightid}/${userId}`);
  }


  //get flights using source and destination
  getFlight(source:string,destination:string){
    return this.http.get<Flights[]>(`${this.baseUrl}/searchFlight/${source}/${destination}`);
  }

  addUser(user:Users){
    return this.http.post<Users>(`${this.baseUrl}/addUser`,user);
  }

  userValid(name:string,password:string){
    return this.http.get(`${this.baseUrl}/login/${name}/${password}`);
  }

  viewTickets(userId:number){
    //return this.http.get<Tickets[]>(`${this.baseUrl}/all/${FlightService.userId}`);
    return this.http.get<Tickets[]>(`${this.baseUrl}/all/${userId}`);
  }

  cancelTicket(bookingId:number){
    return this.http.get(`${this.baseUrl}/cancelFlight/${bookingId}`);
  }
}
