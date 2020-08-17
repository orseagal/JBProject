import { Component, OnInit } from '@angular/core';
import { canComponentDeactivate } from 'src/app/shared/can-deactivate-guard.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from 'src/app/auth/auth.service';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { AngularFireStorage } from '@angular/fire/storage';
import { MatDialog } from '@angular/material/dialog';
import { DialogOverviewDialog } from 'src/app/shared/mat-dialog/mat-dialog';
import { Coupon } from 'src/app/models/coupon';
import { finalize, switchMap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-company-coupons-edit',
  templateUrl: './company-coupons-edit.component.html',
  styleUrls: ['./company-coupons-edit.component.css']
})
export class CompanyCouponsEditComponent implements OnInit, canComponentDeactivate  {

  
  couponForm: FormGroup;
  companyId: number;
  couponId:number;
  error:string=null;
  minDate = new Date();
  imgSrc :string = '/assets/img/Placeholder.jpg';
  selectedImage:any= null;
  isLoading=false;
  coupon:Coupon={};
  couponImage:any;

  constructor(private authService : AuthService ,private dataStorageService: DataStorageService,
    private storage:AngularFireStorage, private dialog: MatDialog,private http: HttpClient, private router:Router,private route: ActivatedRoute) { 

 }

  ngOnInit() {
    this.authService.user.subscribe(user=>{
      if(user!=null){
        this.companyId=user.id;
        console.log(this.companyId);
      }


      this.route.params.subscribe(params=>{
        this.couponId = params['id'];
        this.dataStorageService.fetchProductById(this.couponId).subscribe(productRes=>{
          this.coupon = productRes;
          console.log( this.coupon);
          this.createForm()
        });
      });

     

      // this.dataStorageService.fetchProductById(28).subscribe(res=>{
      //   console.log(res);
      //   this.coupon=res;
      //   this.createForm()
      // })


    })

  }

  

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogOverviewDialog, {
      width: '480px',
      data: {name: 'Coupon has been successfully updated!'}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.animal = result;
    });
  }

//   getImageFromService() {
//     this.getImage(this.coupon.image).subscribe(data => {
//       this.createImageFromBlob(data);
    
//     }, error => {
    
//       console.log(error);
//     });
// }
//   getImage(id: string): Observable<Blob> {
//     var headers: {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' } 
//     return this.http.get(this.coupon.image, {headers: headers ,responseType: "blob"});
// }

// createImageFromBlob(image: Blob) {
//   let reader = new FileReader();
//   reader.addEventListener("load", () => {
//      this.couponImage = reader.result;
//   }, false);

//   if (image) {
//      reader.readAsDataURL(image);
//   }
// }
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
    'title': new FormControl(this.coupon.title, [Validators.required,Validators.minLength(3)]),
    'amount': new FormControl(this.coupon.amount,[Validators.required]),
    'price': new FormControl(this.coupon.price,[Validators.required]),
    'type': new FormControl(this.coupon.type,[Validators.required]),
    'startDate': new FormControl(new Date(this.coupon.startDate),[Validators.required]),
    'endDate': new FormControl(new Date(this.coupon.endDate),[Validators.required]),
    'message': new FormControl(this.coupon.message,[Validators.required]),
    'image': new FormControl(null,[Validators.required]),
  });
}
 


onSubmit(){
  this.error=null;
  var coupon = new Coupon;
  coupon.id = this.coupon.id;
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

        let updateCoupon: Observable<Coupon>
        updateCoupon= this.dataStorageService.updateCoupon(coupon);

        updateCoupon.subscribe(resData=>{
    this.isLoading=false;
    this.openDialog();
    this.router.navigate(['company/coupons'])
  }, errorMessage=>{
    this.error= errorMessage;
    this.isLoading=false;
  
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


}
