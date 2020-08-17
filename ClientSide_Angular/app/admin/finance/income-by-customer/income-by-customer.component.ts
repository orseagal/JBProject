import { Component, OnInit } from '@angular/core';
import { Income } from 'src/app/models/income';
import { DataStorageService } from 'src/app/shared/data-storage.service';

@Component({
  selector: 'app-income-by-customer',
  templateUrl: './income-by-customer.component.html',
  styleUrls: ['./income-by-customer.component.css']
})
export class IncomeByCustomerComponent implements OnInit {
  incomes:Income[]=null;
  searchText:string;
  error:string=null;

    constructor(private dataStorageService:DataStorageService) { }
  
    ngOnInit() {
      
    }

    getIncomeByCustomer(event:HTMLInputElement){
      console.log(event.value);
      const customerId = Number(event.value)
     this.dataStorageService.incomeByCustomerByAdmin(customerId).subscribe(res=>{
      console.log(res);
      this.error=null;
       this.incomes=res;
       if(this.incomes.length==0){
        this.incomes=null;
        this.error='No income history'
        
       }
     },errorMessage=>{
       this.error=errorMessage;
       this.incomes=null;
       console.log(errorMessage);
     })
  
  }
}
