import { Component, OnInit } from '@angular/core';
import { DataStorageService } from '../shared/data-storage.service';
import { Coupon } from '../models/coupon';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  coupons: Coupon[]=null;
  // changeText:boolean=false;
  searchText="";

  constructor(private dataStorage: DataStorageService, private route : ActivatedRoute) { }

  ngOnInit() {
this.dataStorage.fetchProducts().subscribe(couponsRes=>{
 this.coupons= couponsRes;
this.shuffle(this.coupons);
  console.log(couponsRes);
  console.log(this.coupons)


});


this.route.params.subscribe(params=>{ 

  this.searchText = params.search;
  console.log(this.searchText)});


  }

  onCategorySelect(category:string){

  //   this.dataStorage.fetchProductsByType(category).subscribe(couponsRes=>{
  //   this.coupons = couponsRes;
  //   });
  // console.log(category);
  }


  private shuffle(arr) {
    if (arr.length <= 1) return arr;
    var i,
        j,
        temp;
    for (i = arr.length - 1; i > 0; i--) {
        j = Math.floor(Math.random() * (i + 1));
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    console.log(arr);
    return arr;    
};
  

}
