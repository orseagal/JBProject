import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { UserLogin } from '../models/userLogin';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { CartService } from '../cart/cart.service';


@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
 
  isLoading=false;
  error:string=null;
  isRememberMe=false;
  constructor(private authService: AuthService,private router:Router,private cartService:CartService) { }

  ngOnInit() {
  }
  onSubmit(form: NgForm){

    if(!form.valid){
      return;
    }
    const email= form.value.email;
    const password = form.value.password;
    this.isLoading=true;
    let authObs: Observable<UserLogin>

      authObs= this.authService.login(email,password,this.isRememberMe);
      console.log(this.isRememberMe)
    authObs.subscribe(resData=>{
      console.log(resData)
      this.isLoading=false;
      this.error=null;
      this.authService.routueClient();
    },errorMessage=>{
      console.log(errorMessage);
      this.error=errorMessage;
      this.isLoading=false;
    });
    form.reset();
  }

  onSignup(){
    this.router.navigate(['signup']);
  }

}





  // error:string=null;
  // isRememberMe=false;
  // constructor(private authService: AuthService,private router:Router) { }

  // ngOnInit() {
    
  // }


  // onSubmit(form: NgForm){

  //   if(!form.valid){
  //     return;
  //   }
  //   const email= form.value.email;
  //   const password = form.value.password;

  //   let authObs: Observable<UserLogin>

  //     authObs= this.authService.login(email,password,this.isRememberMe);
  //     console.log(this.isRememberMe)
  //   authObs.subscribe(resData=>{
  //     console.log(resData)
     
  //     this.authService.routueClient();
  //   },errorMessage=>{
  //     console.log(errorMessage);
  //     this.error=errorMessage;
  //   });

  //   console.log('submit working')
    
  //   form.reset();
  // }

