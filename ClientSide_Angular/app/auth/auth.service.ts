import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { Router } from "@angular/router";
import { Injectable } from "@angular/core";
import { UserLogin } from "../models/userLogin";
import { catchError, tap } from "rxjs/operators";
import { throwError, BehaviorSubject } from "rxjs";


@Injectable({providedIn:'root'})

export class AuthService{

user = new BehaviorSubject<UserLogin>({username:'guest',authority:'ROLE_GUEST'});
constructor(private http:HttpClient,private router:Router){}


// login(username:string, password:string,isRememberMe:boolean){
//     const headers=new HttpHeaders({Authorization:'Basic '+btoa(username+":"+password)})
// return this.http.get<UserLogin>('http://localhost:8080/couponSystem/api/auth/validateLogin', {headers,withCredentials:true} 
// ).pipe(catchError(this.handleError),tap(resData=>{
//     this.handleAuthentication(resData.id,resData.username,password,resData.email,resData.authority,isRememberMe)
//     console.log(resData)
// }))

// }

login(username:string, password:string,isRememberMe:boolean){
   var userLogin:UserLogin={username,password}
return this.http.post<UserLogin>('http://localhost:8080/couponSystem/api/auth/validateLogin' , userLogin ,{withCredentials:true} 
).pipe(catchError(this.handleError),tap(resData=>{
    this.handleAuthentication(resData.id,resData.username,password,resData.email,resData.authority,isRememberMe)
    console.log(resData)
}))
}

autoLogin(){

    const userLogin:UserLogin=JSON.parse(localStorage.getItem('userData'))
    if(!userLogin){
        return;
    }

    const loadedUser = new UserLogin(
        userLogin.id,
        userLogin.username,
        userLogin.password,
        userLogin.email,
        userLogin.authority
    );

    if(loadedUser){
        this.user.next(loadedUser)
    }

}


signUp(username:string, password:string , email:string, authority:string,isRememberMe:boolean){

    const userLogin:UserLogin={username, password , email, authority};
    var isRememberMe=false
return this.http.post<number>('http://localhost:8080/couponSystem/api/general/createCustomer', userLogin , {withCredentials:true}
).pipe(catchError(this.handleError),tap(resData=>{
    this.login(username,password,isRememberMe)
    this.handleAuthentication(resData,userLogin.username,password,userLogin.email,userLogin.authority,isRememberMe)
    console.log(resData)
}))

}




adminSignUpCompany(username:string, password:string , email:string, authority:string){

    const userLogin:UserLogin={username, password , email, authority};

return this.http.post<UserLogin>('http://localhost:8080/couponSystem/api/admin/createCompany', userLogin , {withCredentials:true}
).pipe(catchError(this.handleError),tap(resData=>{
    console.log(resData)
}))

}
adminSignUpCustomer(username:string, password:string , email:string, authority:string){
const userLogin:UserLogin={username, password , email, authority};
return this.http.post<UserLogin>('http://localhost:8080/couponSystem/api/general/createCustomer', userLogin , {withCredentials:true}
).pipe(catchError(this.handleError),tap(resData=>{
    console.log(resData)
}))

}

logOut(){
    this.user.next({username:'guest',authority:'ROLE_GUEST'});
    this.router.navigate(['/login']);
    localStorage.removeItem('userData');
    localStorage.removeItem('basicauth');
    sessionStorage.removeItem('userData');
    sessionStorage.removeItem('basicauth');
    
     return this.http.get('http://localhost:8080/couponSystem/api/auth/logout' ,{withCredentials:true})
     .subscribe(res=>{
         console.log(res) 
        
     })

}


private handleAuthentication(id: number,username:string ,password:string,email:string, authority: string,isRememberMe:boolean){
   const user:UserLogin={id,username,email,authority}
   let authString = 'Basic '+btoa(username+":"+password);
if(isRememberMe){
    localStorage.setItem('userData' , JSON.stringify(user));
    localStorage.setItem('basicauth', authString);
}else{
    sessionStorage.setItem('userData' , JSON.stringify(user));
    sessionStorage.setItem('basicauth', authString);
}

this.user.next(user);


}


private handleError(errorRes: HttpErrorResponse){
    console.log("handle error");
let errorMessage = 'an unknown error occurred!';
if(!errorRes.error.message || !errorRes.error){
    return throwError(errorMessage);
}

switch(errorRes.error.message){
    case 'INVALID_USERNAME_OR_PASSWORD':
        errorMessage ='Invalid username or password';
        break;
    case 'EMAIL_EXIST':
        errorMessage = 'The email you inserted already exist, please choose a different one'; 
        break;
    case 'NAME_EXIST':
        errorMessage = 'The name you inserted already exist, please choose a different one'
}
return throwError(errorMessage);
}

routueClient(){
     var userRes: UserLogin;
    this.user.subscribe(user=>{
        userRes= user;
    })
    
switch(userRes.authority){

case 'ROLE_ADMIN':
    this.router.navigate(['/admin'])
    break;
case 'ROLE_COMPANY':
    this.router.navigate(['/company'])
    break;
case 'ROLE_CUSTOMER':
    this.router.navigate(['/products'])        
    break;
}

}

}


