import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer';
import { AuthService } from 'src/app/auth/auth.service';
import { UserLogin } from 'src/app/models/userLogin';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent implements OnInit {
 customer:UserLogin;

  constructor(private authService: AuthService) { }

  ngOnInit() {
  this.authService.user.subscribe(user=>{
    console.log(user)
    this.customer= user;
  });
  }



}
