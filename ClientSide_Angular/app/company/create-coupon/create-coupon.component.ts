import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Coupon } from 'src/app/models/coupon';
import { AuthService } from 'src/app/auth/auth.service';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { Observable } from 'rxjs';
import { AngularFireStorage } from '@angular/fire/storage';
import { finalize } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { DialogOverviewDialog } from 'src/app/shared/mat-dialog/mat-dialog';
import { canComponentDeactivate } from 'src/app/shared/can-deactivate-guard.service';




@Component({
  selector: 'app-create-coupon',
  templateUrl: './create-coupon.component.html',
  styleUrls: ['./create-coupon.component.css']
})
export class CreateCouponComponent implements OnInit, canComponentDeactivate {

  couponForm: FormGroup;
  companyId: number;
  error:string=null;
  minDate = new Date();
  imgSrc :string = '/assets/img/Placeholder.jpg';
  selectedImage:any= null;
  isLoading=false;

  ngOnInit() {
    this.authService.user.subscribe(user=>{
      if(user!=null){
        this.companyId=user.id;
        console.log(this.companyId);
      }
     
    })
    this.createForm()
  }

  constructor(private authService : AuthService ,private dataStorageService: DataStorageService, private storage:AngularFireStorage, public dialog: MatDialog) { 

  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogOverviewDialog, {
      width: '480px',
      data: {name: 'Coupon has been successfully created!'}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.animal = result;
    });
  }




  showPreview(event:any){
    if(event.target.files && event.target.files[0]){
      const reader = new FileReader();
      reader.onload = (e:any)=> this.imgSrc = e.target.result;
      reader.readAsDataURL(event.target.files[0]);
      this.selectedImage = event.target.files[0];
      console.log(this.selectedImage)
    }else{
      this.imgSrc='/assets/img/Placeholder.jpg';
      this.selectedImage= null;
    }

   
  }

createForm(){
  this.couponForm = new FormGroup({
    'title': new FormControl(null, [Validators.required,Validators.minLength(3)]),
    'amount': new FormControl(null,[Validators.required]),
    'price': new FormControl(null,[Validators.required]),
    'type': new FormControl(null,[Validators.required]),
    'startDate': new FormControl(null,[Validators.required]),
    'endDate': new FormControl(null,[Validators.required]),
    'message': new FormControl(null,[Validators.required]),
    'image': new FormControl(null,[Validators.required]),
  });
}
 


onSubmit(){
  this.error=null;
  var coupon = new Coupon;
  coupon.companyId = this.companyId;
  coupon.amount= this.couponForm.value.amount;
  coupon.price= this.couponForm.value.price;
  coupon.title= this.couponForm.value.title;
  coupon.title = coupon.title.trim();
  coupon.type= this.couponForm.value.type;
  coupon.message = this.couponForm.value.message;
  coupon.startDate= this.couponForm.value.startDate;
  coupon.endDate = this.couponForm.value.endDate;
  console.log(coupon);
  this.isLoading=true;
  var filePath = this.couponForm.value.type +'/'+ this.selectedImage.name;

  const fileRef = this.storage.ref(filePath); 
  this.storage.upload(filePath, this.selectedImage).snapshotChanges().pipe(
    finalize(()=>{
      fileRef.getDownloadURL().subscribe((url)=>{
        coupon.image=url;
        console.log(coupon.image);

        let couponCreate: Observable<Coupon>
        couponCreate= this.dataStorageService.createCoupon(coupon);

  couponCreate.subscribe(resData=>{
    console.log(resData)
    this.isLoading=false;
    this.openDialog();
  }, errorMessage=>{
    this.error= errorMessage;
    this.isLoading=false;
    window.scroll(0,0);
  //   let scrollToTop = window.setInterval(() => {
  //     let pos = window.pageYOffset;
  //     if (pos > 0) {
  //         window.scrollTo(0, pos - 20); // how far to scroll on each step
  //     } else {
  //         window.clearInterval(scrollToTop);
  //     }
  // }, 16);
   
  })

      })
    })
  ).subscribe();

  
  

  this.couponForm.reset();
  // form.setValue({
  //   image:'',
  //   type:""
  // })
  this.imgSrc = '/assets/img/Placeholder.jpg';
  this.selectedImage=null;
}

canDeactivate(): boolean | Observable<boolean> | Promise<boolean>{
  if(this.couponForm.touched){
    return confirm('Do you want to discard the changes?');

  }else{
    return true;
  }
}

//confirm('Do you want to discard the changes?');
}