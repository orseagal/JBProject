import { Component, OnInit } from '@angular/core';
import { UserLogin } from 'src/app/models/userLogin';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css']
})
export class CompanyProfileComponent implements OnInit {

  company:UserLogin;

  constructor(private authService: AuthService) { }

  ngOnInit() {
  this.authService.user.subscribe(user=>{
    console.log(user)
    this.company= user;
  });
  }

}
