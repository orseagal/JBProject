import { Component, OnInit } from '@angular/core';
import { Income } from 'src/app/models/income';
import { DataStorageService } from 'src/app/shared/data-storage.service';

@Component({
  selector: 'app-income-by-company',
  templateUrl: './income-by-company.component.html',
  styleUrls: ['./income-by-company.component.css']
})
export class IncomeByCompanyComponent implements OnInit {
  incomes:Income[]=null;
  searchText:string;
  error:string=null;

    constructor(private dataStorageService:DataStorageService) { }
  
    ngOnInit() {
      
    }

getIncomeByComapny(event:HTMLInputElement){
    console.log(event.value);
    const companyId = Number(event.value)
   this.dataStorageService.incomeByCompanyByAdmin(companyId).subscribe(res=>{
    console.log(res);
    this.error=null;
     this.incomes=res;
   },errorMessage=>{
     this.error=errorMessage;
     this.incomes=null;
     console.log(errorMessage);
   })

}

}
