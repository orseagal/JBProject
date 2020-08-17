import { Component, OnInit, TemplateRef } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { Coupon } from 'src/app/models/coupon';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { DialogOverviewDialog } from 'src/app/shared/mat-dialog/mat-dialog';
import { MatDialog } from '@angular/material/dialog';
import { Router, NavigationExtras } from '@angular/router';

@Component({
  selector: 'app-company-coupons',
  templateUrl: './company-coupons.component.html',
  styleUrls: ['./company-coupons.component.css']
})
export class CompanyCouponsComponent implements OnInit {
companyId:number;
coupons:Coupon[] =null;
modalRef: BsModalRef;
message: string;
//saveCheck=true;

  constructor(private authService:AuthService, private dataStorageService:DataStorageService,
     private modalService: BsModalService, private dialog: MatDialog ,private router:Router) { }

  ngOnInit() {
    this.authService.user.subscribe(user=>{
      this.companyId= user.id;
    })
    this.fetchCoupons()
  }

  // openDialog(): void {
  //   const dialogRef = this.dialog.open(DialogOverviewDialog, {
  //     width: '480px',
  //     data: {name: 'Amount has been successfully updated!'}
  //   });
  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log('The dialog was closed');
  //   });
  // }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {class: 'modal-sm'});
  }
 
  confirm(coupon:Coupon): void {
    this.onDelete(coupon);
    this.modalRef.hide();
  }
 
  decline(): void {
    this.modalRef.hide();
  }

  // onSaveChanges(coupon:Coupon){
  //   console.log(coupon)
  // console.log(couponId,amount)
  // this.dataStorageService.updateAmount(couponId,amount).subscribe(saveResult=>{
  //   console.log(saveResult);
  // this.openDialog()
  //   this.fetchCoupons();
  // },errorMessage=>{
  //   console.log(errorMessage);
  // })
  // }

  onDelete(coupon:Coupon){
    // var isDelete = confirm("Are You Sure You Want To Delete Customer " +customer.name +" ?");
    
      console.log("delete")
      this.dataStorageService.deleteCopuon(this.companyId, coupon.id).subscribe(result=>{
        this.fetchCoupons();
        console.log("coupon deleted")
      })
    
  }
  
  fetchCoupons(){
    this.dataStorageService.fetchCompanyCoupons(this.companyId).subscribe(coupons=>{
      this.coupons=coupons;
      console.log(coupons)
    })
  }

// onEdit(coupon:Coupon){
//   let navigationExtras: NavigationExtras = {
//     queryParams: {
//         "coupon": JSON.stringify(coupon)
//     }
//   };

// this.router.navigate(['company/coupon-edit'],  navigationExtras);
// }


// saveCheck(amount:number, couponId:number){
// // console.log(amount);
// // console.log(couponId);
// if(this.CouponsCheck!=null){
//   var TempCoupon:Coupon
//   this.CouponsCheck.forEach(coupon=>{ 
//     if(coupon.id==couponId){
//       TempCoupon=coupon;
//       }
//   })
//     if(TempCoupon.amount==amount){
//       return true;
//     }else{
//       return false
//     }
//   }
//   }


}






