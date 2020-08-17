import { Component, OnInit } from '@angular/core';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { ActivatedRoute } from '@angular/router';
import { Coupon } from 'src/app/models/coupon';

@Component({
  selector: 'app-products-category',
  templateUrl: './products-category.component.html',
  styleUrls: ['./products-category.component.css']
})
export class ProductsCategoryComponent implements OnInit {
  coupons:Coupon[]=null;
  category:string;
  constructor(private dataStorage: DataStorageService, private route : ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params=>{ 
      var category = params['category'];
      console.log(category)
    
      this.dataStorage.fetchProductsByType(category).subscribe(couponsRes=>{
        this.coupons = couponsRes;
        console.log(couponsRes)
        });
    });

     
      // console.log(this.category);
      }

  }


  // onCategorySelect(category:string){
  //   this.dataStorage.fetchProductsByType(category).subscribe(couponsRes=>{
  //   this.coupons = couponsRes;
  //   });
  // console.log(category);
  // }
  

