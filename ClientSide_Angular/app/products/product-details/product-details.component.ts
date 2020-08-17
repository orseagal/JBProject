import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { Coupon } from 'src/app/models/coupon';
import { CartService } from 'src/app/cart/cart.service';
import { AuthService } from 'src/app/auth/auth.service';


@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  oldPrice:number
  productId:number
  discountPercent:number;
  coupon:Coupon=null;
  couponAddedToCart:Coupon[]
  cartItemCount:number=0;
  cartUserCheck:boolean;
  authLoginName:string;
  constructor(private route: ActivatedRoute,private dataStorageService: DataStorageService,
     private cartService:CartService,private authService:AuthService) { }

  ngOnInit() {
    this.authService.user.subscribe(user=>{
      if(user!=null){
        this.authLoginName = user.username;
        if(user.authority=="ROLE_COMPANY"|| user.authority=="ROLE_ADMIN"){
          this.cartUserCheck=true;
        }
        console.log(this.authLoginName);
      }
     
    });

    this.route.params.subscribe(params=>{
      this.productId = params['id'];
      this.dataStorageService.fetchProductById(this.productId).subscribe(productRes=>{
        this.coupon = productRes;
        this.discountPercent=30;
        this.oldPriceCalculator(this.coupon)
        console.log(this.coupon);
      });
    });
  }

  onAddToCart(coupon:Coupon){
   this.couponAddedToCart =  this.dataStorageService.getProductFromCart(this.authLoginName);
   if(this.couponAddedToCart==null){
    this.couponAddedToCart=[];
    this.couponAddedToCart.push(coupon);
    this.dataStorageService.addProductToCart(this.couponAddedToCart,this.authLoginName);


   } else{

    let tempProducts = this.couponAddedToCart.find(p=> p.id==coupon.id);
    if(tempProducts==null){
      this.couponAddedToCart.push(coupon);
      this.dataStorageService.addProductToCart(this.couponAddedToCart,this.authLoginName);

    }

   }
   this.cartItemCount =this.couponAddedToCart.length;
   this.cartService.updateCartCount(this.cartItemCount);
    
  }
  


  oldPriceCalculator(coupon: Coupon){
    if(coupon!= null){
      this.oldPrice= coupon.price + ((coupon.price * this.discountPercent)/100);
      // console.log(this.oldPrice) 
    }
  }

}
