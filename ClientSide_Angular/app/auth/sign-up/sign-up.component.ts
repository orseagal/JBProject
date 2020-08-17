import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { NgForm, FormGroup, FormBuilder, Validators} from '@angular/forms';
import { Observable } from 'rxjs';
import { UserLogin } from 'src/app/models/userLogin';
import { CustomValidators } from 'src/app/shared/custom-validators';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  
  ngOnInit(){

  }
  public frmSignup: FormGroup;
  isLoading=false;
  error:string=null;
  isRememberMe=false;
  userRole= "ROLE_CUSTOMER";

  constructor(private fb: FormBuilder, private authService: AuthService,private router:Router) {
    this.frmSignup = this.createSignupForm();
  }

  createSignupForm(): FormGroup {
    return this.fb.group(
      {
        email: [
          null,
          Validators.compose([Validators.email, Validators.required])
        ],

        name: [
          null,
          Validators.compose([Validators.minLength(6), Validators.required])
        ],

        password: [
          null,
          Validators.compose([
            Validators.required,
            // check whether the entered password has a number
            CustomValidators.patternValidator(/\d/, {
              hasNumber: true
            }),
            // check whether the entered password has upper case letter
            CustomValidators.patternValidator(/[A-Z]/, {
              hasCapitalCase: true
            }),
            // check whether the entered password has a lower case letter
            CustomValidators.patternValidator(/[a-z]/, {
              hasSmallCase: true
            }),
           
         
            Validators.minLength(8)
          ])
        ],
        confirmPassword: [null, Validators.compose([Validators.required])]
      },
      {
        // check whether our password and confirm password match
        validator: CustomValidators.passwordMatchValidator
      }
    );
  }

  submit() {
    // do signup or something

    console.log(this.frmSignup.value);

    if(!this.frmSignup.valid){
      return;
    }
    var email:string= this.frmSignup.value.email;
    email = email.trim();
    var password:string = this.frmSignup.value.password;
    password= password.trim();
    var name:string = this.frmSignup.value.name;
    name = name.trim();
    let authObs: Observable<number>
    this.isLoading=true;
    authObs= this.authService.signUp(name,password,email,this.userRole,this.isRememberMe);

    authObs.subscribe(resData=>{
      console.log(resData)
      this.isLoading=false;
      this.router.navigate(['/products'])
    },errorMessage=>{
      console.log(errorMessage);
      this.error=errorMessage;
      this.isLoading=false;
    });

    console.log('submit working')
    this.frmSignup.reset();
  }


      }
      
     
  
  






