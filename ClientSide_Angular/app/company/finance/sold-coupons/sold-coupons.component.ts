import { Component, OnInit } from '@angular/core';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { Coupon } from 'src/app/models/coupon';

@Component({
  selector: 'app-sold-coupons',
  templateUrl: './sold-coupons.component.html',
  styleUrls: ['./sold-coupons.component.css']
})
export class SoldCouponsComponent implements OnInit {
coupons:Coupon[]=null;
subTotal:number;
error:String;
isLoading:boolean=true;

constructor(private dataStorageService:DataStorageService) { }

ngOnInit() {
    this.dataStorageService.SoldCouponsByCompany().subscribe(res=>{
      this.coupons=res;
this.totalSumOfSales(this.coupons);
this.isLoading=false;
    },errorMessage=>{
      this.error=errorMessage;
      console.log(errorMessage);
    })
  }

totalSumOfSales(coupons:Coupon[]){
  if(coupons!=null){
    var total=0;
    for(let i in coupons){
      total= total+ (coupons[i].price)
    }
    return this.subTotal=total;
  }
}

}
