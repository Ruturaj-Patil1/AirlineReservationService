import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FlightService } from '../../service/flight.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //formgroup object
  loginForm: FormGroup;
 
  fname: string[];
  submitted: boolean = false;
  // Constructor Dependency Injection
 
  // FormBuilder to build form elements with defaut values and validations
  // Router service to navigate programmatically from component to other
  constructor(private formBuilder: FormBuilder, private router: Router,
     private flightService: FlightService) { }

  
  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      name: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  verifyLogin() {
    if (this.loginForm.invalid) {
      return;
    }

    let username = this.loginForm.controls.name.value;
    let password = this.loginForm.controls.password.value;

    console.log(username + password);
    this.flightService.userValid(username, password).subscribe(data => {console.log(data);
      if (data > 0) {
        alert(`${this.loginForm.controls.name.value} Logged In successfully`);
        
        localStorage.userid = data;
        sessionStorage.userid = data;
        this.router.navigate(['/home']);
      }
      else {
        console.log("invalid login");
        return;

      }
    })
  } // end of verifyLogin()

  invalidLogin: boolean = false;
}


