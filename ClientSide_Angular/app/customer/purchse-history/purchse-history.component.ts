import { Component, OnInit } from '@angular/core';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { Coupon } from 'src/app/models/coupon';
import { AuthService } from 'src/app/auth/auth.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-purchse-history',
  templateUrl: './purchse-history.component.html',
  styleUrls: ['./purchse-history.component.css']
})
export class PurchseHistoryComponent implements OnInit {
purchaseHistory:Coupon[]=[];
userId:number;

  constructor(private dataStorageService:DataStorageService, private authService:AuthService) { }

  ngOnInit() {

    this.authService.user.subscribe(user=>{
      if(user.authority=='ROLE_CUSTOMER' && user.id!=null){
    this.userId=user.id;
    let purchseHistory:Observable<Coupon[]>;
    purchseHistory = this.dataStorageService.fetchCustomerCoupons(this.userId);
    purchseHistory.subscribe(res=>{
      this.purchaseHistory = res;
      console.log(this.purchaseHistory);
    });
  }
      }
    );
   
    }
}
