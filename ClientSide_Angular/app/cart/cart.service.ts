import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { DataStorageService } from '../shared/data-storage.service';
import { AuthService } from '../auth/auth.service';

@Injectable({providedIn:'root'})
export class CartService {
   // authLoginName:string;

    // ngOnInit() {
    //     this.authService.user.subscribe(res=>{
    //         this.authLoginName = res.name;
    //         var productFromCart =  this.dataStorageService.getProductFromCart(this.authLoginName);
    //         if(productFromCart!=null){
    //             this.updateCartCount(productFromCart.length)
    //         }
    //     });
       
    // }
 
    private currentCartCount = new BehaviorSubject(0);
    currentMessage = this.currentCartCount.asObservable();

    // constructor(private dataStorageService: DataStorageService, private authService:AuthService){
    // }

    updateCartCount(count:number){
    this.currentCartCount.next(count);
            
    }
}