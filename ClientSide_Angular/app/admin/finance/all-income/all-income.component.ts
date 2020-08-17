import { Component, OnInit } from '@angular/core';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { Income } from 'src/app/models/income';

@Component({
  selector: 'app-all-income',
  templateUrl: './all-income.component.html',
  styleUrls: ['./all-income.component.css']
})
export class AllIncomeComponent implements OnInit {
incomes:Income[]=null;
searchText:string;
isLoading:boolean=true;
error:string=null;
subTotal:number=null;

  constructor(private dataStorageService:DataStorageService) { }

  ngOnInit() {
    this.dataStorageService.allincomes().subscribe(res=>{
      this.incomes=res;
      this.AllIncomesSum(this.incomes);
      this.isLoading=false;
    },errorMessage=>{
      this.error=errorMessage;
      this.incomes=null;
      console.log(errorMessage);
    })
  }


AllIncomesSum(income:Income[]){
var total:number=0;
for(let i in income){
total = total +(income[i].amount)
}
return this.subTotal=total;
}

}
