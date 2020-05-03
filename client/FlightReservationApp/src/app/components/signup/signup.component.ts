import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FlightService } from '../../service/flight.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signUpForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router,
     private flightService : FlightService) { }

  ngOnInit(){
    this.signUpForm = this.formBuilder.group({

      name:['',Validators.required],
      username:['',Validators.required],
      phoneNum:['',Validators.required],
      age:['',Validators.required],
      password: ['', [Validators.required,Validators.minLength(4)]],
    });
  }

  verifyLogin(){
    // this.submitted = true;
    if(this.signUpForm.invalid){
      return;
    }
   
      console.log(this.signUpForm.value);

      this.flightService.addUser(this.signUpForm.value).subscribe(data =>{
        console.log(data);
        alert(`${this.signUpForm.controls.name.value} SignUped successfully ..!`);
        this.router.navigate(['/login']);
      },
      err =>{
        console.log(err.stack);
      })
      // this.userService.createNewUser(this.signUpForm.value).subscribe(data => {
      //   alert(`${this.signUpForm.controls.name.value} record is added successfully ..!`);
      //   this.router.navigate(['/login']);
  
      // }, err => {
      //   console.log(err.stack);
      // })
    }
    
  }
