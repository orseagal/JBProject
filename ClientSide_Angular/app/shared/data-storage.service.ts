import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { Customer } from '../models/customer';
import { Company } from '../models/company';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { PasswordBeans } from '../models/passwordBean';
import { Coupon } from '../models/coupon';
import { PurchaseBean } from '../models/purchaseBean';
import { UserLogin } from '../models/userLogin';
import { Income } from '../models/income';

@Injectable({
    providedIn:'root'
})
export class DataStorageService{

constructor(private http: HttpClient){}
// ----Products---- //

fetchProducts(){
return this.http.get<Coupon[]>('http://localhost:8080/couponSystem/api/general/getAll', {withCredentials:true})
.pipe(catchError(this.handleError));

}

fetchProductsByType(type: string){
return this.http.get<Coupon[]>('http://localhost:8080/couponSystem/api/general/byCategory/' + type ,{withCredentials:true})
.pipe(catchError(this.handleError));
}

fetchProductById(id :number){
return this.http.get<Coupon>('http://localhost:8080/couponSystem/api/general/getById/' + id , {withCredentials:true})
.pipe(catchError(this.handleError));

}

fetchCustomerCoupons(customerId:number){
    return this.http.get<Coupon[]>('http://localhost:8080/couponSystem/api/customers/customerCoupons/' + customerId, {withCredentials:true})
    .pipe(catchError(this.handleError));
}

fetchCartCouponsFromDb(cartCopuons:number[]){
return this.http.post<Coupon[]>('http://localhost:8080/couponSystem/api/customers/cartCoupons' , cartCopuons, {withCredentials:true})
.pipe(catchError(this.handleError));
}

// purchaseProduct(customerId:number,coupons:number[]){
// return this.http.post<PurchaseBean>('http://localhost:8080/couponSystemApi/rest/coupons/purchase/'+customerId ,coupons, {withCredentials:true})
// .pipe(catchError(this.handleError));

// }

purchaseProduct(customerId:number,coupons:number[]){
    return this.http.post<PurchaseBean>('http://localhost:8080/couponSystem/api/customers/purchase/'+customerId ,coupons, {withCredentials:true})
    .pipe(catchError(this.handleError));
    
    }


// ----Customers---- //
fetchCustomers(){
    return this.http.get<Customer[]>('http://localhost:8080/couponSystem/api/admin/allCustomers', {withCredentials:true})
}


deleteCustomer(userId:number){
   // const headers=new HttpHeaders({Authorization:'Basic '+btoa("admin:test123")})
return this.http.delete('http://localhost:8080/couponSystem/api/admin/' + userId, {withCredentials:true})
.pipe(catchError(this.handleError))
}

updateCustomerPassword(password:string, newPassword:string, id:number, clientType:string){
    const passwordBean:PasswordBeans={id ,clientType ,password, newPassword}
    console.log(passwordBean)
    return this.http.put('http://localhost:8080/couponSystem/api/customers/update-password' , passwordBean, { withCredentials:true, responseType: 'json'} )
    .pipe(catchError(this.handleError))
}

// ----Companies---- //
fetchCompanies(){
return this.http.get<Company[]>('http://localhost:8080/couponSystem/api/admin/allCompanies', {withCredentials:true})
}

createCompany(name:string, password:string , email:string){
    const company:Company={name,password,email};
    return this.http.post<number>('http://localhost:8080/couponSystem/api/admin/createCompany' , company, {withCredentials:true})
    .pipe(catchError(this.handleError));

}

createCoupon(coupon:Coupon){
    console.log(coupon);
return this.http.post<Coupon>('http://localhost:8080/couponSystem/api/companies/createCoupon' , coupon , {withCredentials:true})
.pipe(catchError(this.handleError));

}

updateCoupon(coupon:Coupon){
    return this.http.put('http://localhost:8080/couponSystem/api/companies/updateCoupon/' , coupon , {withCredentials:true})
    .pipe(catchError(this.handleError));
    }

updateAmount(couponId:number, amount:number){
return this.http.get('http://localhost:8080/couponSystem/api/companies/updateAmount/' + couponId +"/"+  amount , {withCredentials:true,responseType: 'json'})
.pipe(catchError(this.handleError));
}

fetchCompanyCoupons(companyId:number){
return this.http.get<Coupon[]>('http://localhost:8080/couponSystem/api/companies/companyCoupons/' +companyId , {withCredentials:true} )
.pipe(catchError(this.handleError));

}

SoldCouponsByCompany(){
    return this.http.get<Coupon[]>('http://localhost:8080/couponSystem/api/companies/soldCoupons' , {withCredentials:true})
    .pipe(catchError(this.handleError))
}

deleteCopuon(companyId:number, couponId:number){
return this.http.delete<Coupon>('http://localhost:8080/couponSystem/api/companies/'+ companyId +'/'+ couponId ,{withCredentials:true} )
.pipe(catchError(this.handleError));
}

updateCompanyPassword(password:string, newPassword:string, id:number, clientType:string){
    const passwordBean:PasswordBeans={id ,clientType ,password, newPassword}
    console.log(passwordBean)
    return this.http.put('http://localhost:8080/couponSystem/api/companies/update-password' , passwordBean, { withCredentials:true, responseType: 'json'} )
    .pipe(catchError(this.handleError))
}


deleteCompany(companyId:number){
    return this.http.delete('http://localhost:8080/couponSystem/api/admin/' + companyId, {withCredentials:true})
}

//-----------finance-----------

allincomes(){
    return this.http.get<Income[]>('http://localhost:8080/couponSystem/api/admin/allIncomes' , {withCredentials:true})
    .pipe(catchError(this.handleError))
}

incomeByCustomerByAdmin(customerId:number){
return this.http.get<Income[]>('http://localhost:8080/couponSystem/api/admin/incomeByCustomer/'+ customerId, {withCredentials:true})
.pipe(catchError(this.handleError))
}

incomeByCompanyByAdmin(companyId:number){
    return this.http.get<Income[]>('http://localhost:8080/couponSystem/api/admin/incomeByCompany/'+ companyId, {withCredentials:true})
    .pipe(catchError(this.handleError))
    }

incomeByCompany(){
    return this.http.get<Income[]>('http://localhost:8080/couponSystem/api/companies/incomeByCompany/', {withCredentials:true})
    .pipe(catchError(this.handleError))
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
        errorMessage = 'The name you inserted already exist, please choose a different one';
        break;
    case 'PASSWORD_NOT_MATCHED':
        errorMessage = 'Sorry but the password you inserted doesnt match your current password';
        break;
    case 'TITLE_EXIST':
        errorMessage = 'Title is already exist - please choose a different one';
        break;
    case 'the id you insert not exists':
        errorMessage = 'The id you insert not exists';
        break;
       
}       
return throwError(errorMessage);
}


addProductToCart(prodcuts: any, userLoginName:string) {
    localStorage.setItem("product " + userLoginName, JSON.stringify(prodcuts));
  }

getProductFromCart(userLoginName:string) {
    return JSON.parse(localStorage.getItem('product '+userLoginName));
  }

removeAllProductFromCart(userLoginName:string) {
    return localStorage.removeItem('product '+userLoginName);
  }

  
}