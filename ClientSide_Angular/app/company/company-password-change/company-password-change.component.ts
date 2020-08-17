import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { NotificationDialogService } from 'src/app/shared/notification-dialog/notification-dialog.service';
import { CustomValidators } from 'src/app/shared/custom-validators';
import { Observable } from 'rxjs';
import { PasswordBeans } from 'src/app/models/passwordBean';

@Component({
  selector: 'app-company-password-change',
  templateUrl: './company-password-change.component.html',
  styleUrls: ['./company-password-change.component.css']
})
export class CompanyPasswordChangeComponent implements OnInit {
  public frmPassword: FormGroup;
  isLoading=false;
  error:string=null;
  clientType= "COMPANY";
  userId:number;

  ngOnInit(){
    this.authService.user.subscribe(user=>{
      if(user){
        this.userId = user.id;
        console.log(this.userId);
      }
    
    });

  }

  constructor(private fb: FormBuilder, private authService: AuthService, private dataStorageService: DataStorageService ,private router:Router, private notificationDialogService: NotificationDialogService) {
    this.frmPassword = this.createSignupForm();
  }

  public openNotificationtionDialog() {
    this.notificationDialogService.confirm('Successful', 'password has successfully been changed')
  }


  createSignupForm(): FormGroup {
    return this.fb.group(
      {
        
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

        oldPassword: [
          null,
          Validators.compose([Validators.minLength(8), Validators.required])
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

    console.log(this.frmPassword.value);

    if(!this.frmPassword.valid){
      return;
    }
    const password = this.frmPassword.value.oldPassword;
    const newPassword = this.frmPassword.value.password;

    let authObs: Observable<PasswordBeans>
    this.isLoading=true;
    authObs= this.dataStorageService.updateCompanyPassword(password,newPassword,this.userId,this.clientType);

    authObs.subscribe(resData=>{
      console.log(resData)
      this.isLoading=false;
      this.openNotificationtionDialog();
    },errorMessage=>{
      console.log(errorMessage);
      this.error=errorMessage;
      this.isLoading=false;
    });

    console.log('password changed')
    this.frmPassword.reset();
  }


}
