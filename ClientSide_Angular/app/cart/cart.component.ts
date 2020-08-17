import { Component, OnInit, TemplateRef } from '@angular/core';
import { CartService } from './cart.service';
import { Coupon } from '../models/coupon';
import { DataStorageService } from '../shared/data-storage.service';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { AuthService } from '../auth/auth.service';
import { UserLogin } from '../models/userLogin';
import { Router } from '@angular/router';
import { PurchaseBean } from '../models/purchaseBean';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems:Coupon[]=[];
  subtotal:number;
  modalRef: BsModalRef;
  userLogin:UserLogin= new UserLogin();
  idArray:number[]=[];
  checkoutError:boolean=true;
  checkoutClick:boolean=false;
  // checkoutValidationSub = new Observable<boolean>((res)=>{
  //   console.log('checkout validation')
  //   res.next(this.checkoutError=this.checkoutValidation(this.idArray,this.cartItems))
  // })

  constructor(private cartService: CartService ,private dataStorageService: DataStorageService
     ,private modalService: BsModalService, private authService:AuthService, private router:Router) { }

  ngOnInit() {
    console.log(this.cartItems);
    this.authService.user.subscribe(user=>{
      if(user.authority!='ROLE_GUEST'){
        this.userLogin.username = user.username;
        this.userLogin.authority = user.authority;
        this.userLogin.id= user.id;
      }else{
        this.userLogin.username = user.username;
        this.userLogin.authority = user.authority;
        this.checkoutError=false;
      }

        console.log(this.userLogin.username);
        console.log(this.userLogin.id);
        var getItems = this.dataStorageService.getProductFromCart(this.userLogin.username);
        console.log(getItems);
        if(getItems!=null){
          var couponsId:number[]=[];
          for(let i in getItems){
            couponsId.push(getItems[i].id);
            console.log(couponsId);
          } 
          if(user.authority=='ROLE_CUSTOMER'){
            this.dataStorageService.fetchCartCouponsFromDb(couponsId).subscribe(copuonResault=>{
              this.cartItems=copuonResault;
              console.log(copuonResault);
            });
          }
          this.cartItems=getItems;
          console.log(this.cartItems);
        }else{
          this.cartItems=[];
        }
        this.calculateTotalPrice(this.cartItems);
        console.log(this.cartItems);
        
        if(this.userLogin.authority=='ROLE_CUSTOMER'){
          var userPurchaseHistory = this.dataStorageService.fetchCustomerCoupons(this.userLogin.id).subscribe(res=>{
            if(res!=null){
             for(let i in res){
               this.idArray.push(res[i].id);
             }
             console.log(this.idArray);
             this.checkoutError=false;
            }
            //this.checkoutValidationSub.subscribe();
            
            this.checkoutError= this.checkoutValidation(this.idArray,this.cartItems);
          },()=>{
           this.checkoutError=true;
           console.log('error')
           this.router.navigate(['/page-not-found']);
         });

        }
        if(this.userLogin.authority=='ROLE_GUEST'){
          console.log("true")
          this.checkoutError=this.checkoutValidation(this.idArray,this.cartItems);
        }
       console.log(userPurchaseHistory);
       console.log(this.idArray);

       
    });

   
    // this.dataStorageService.removeAllProductFromCart();
    // this.dataStorageService.addProductToCart(this.cartItems);
  }

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

  onDelete(coupon: Coupon) {
    console.log(coupon);
    this.cartItems = this.dataStorageService.getProductFromCart(this.userLogin.username);
    this.cartItems.splice(this.cartItems.findIndex(find => find.id ==coupon.id ),1);
    console.log(this.cartItems.indexOf(coupon));
    console.log(this.cartItems.findIndex(find => find.id ==coupon.id ))
    
    console.log(this.cartItems.length)
    console.log(this.cartItems)
    this.dataStorageService.removeAllProductFromCart(this.userLogin.username);
    this.dataStorageService.addProductToCart(this.cartItems, this.userLogin.username);
    var productsFromCart = this.dataStorageService.getProductFromCart(this.userLogin.username);
    if(productsFromCart!=null){
      this.cartItems = productsFromCart;
    }
    var cartItemsCount =this.cartItems.length;
    console.log(cartItemsCount);
    this.cartService.updateCartCount(cartItemsCount);
    this.cartItems = this.dataStorageService.getProductFromCart(this.userLogin.username);
    this.calculateTotalPrice(this.cartItems);
    //this.checkoutValidationSub.subscribe();
    if(this.userLogin.authority!='ROLE_GUEST'){
      this.checkoutError= this.checkoutValidation(this.idArray,this.cartItems);
    }
    this.ngOnInit();
  }
 
  calculateTotalPrice(allCoupons: Coupon[]){

    var total=0;
    for(let i in allCoupons){
      total = total + (allCoupons[i].price)

    } 
    this.subtotal = total;
  }

  onCheckout(){
    if(this.userLogin.authority=='ROLE_GUEST'){
      this.router.navigate(['login']);
    }
    if(this.userLogin.authority=='ROLE_CUSTOMER'){
      this.checkoutClick=true;
      var couponsId:number[]=[];
      for(let i in this.cartItems){
        couponsId.push(this.cartItems[i].id);
        console.log(couponsId);
      } 
      var customerId:number=this.userLogin.id;
      var purchaseCart:PurchaseBean={couponsId,customerId};
      console.log(purchaseCart);
      let purchaseObs:Observable<PurchaseBean>;
      purchaseObs = this.dataStorageService.purchaseProduct(customerId,couponsId);

      purchaseObs.subscribe(res=>{
        this.dataStorageService.removeAllProductFromCart(this.userLogin.username);
        this.cartItems=[];
        this.cartService.updateCartCount(0);
      },errorMessage=>{
        console.log(errorMessage);
      });
    }
    console.log('on checkout')
  }

  checkoutValidation(purchaseItemsIdArray:number[], cartItems:Coupon[]):boolean{
    for(let i in cartItems){
      if(purchaseItemsIdArray.includes(cartItems[i].id)|| !cartItems[i].avaliable){
        return true;
      }
    }

  }

}
