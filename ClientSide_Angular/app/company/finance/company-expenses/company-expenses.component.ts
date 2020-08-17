import { Component, OnInit, OnDestroy } from '@angular/core';
import { Income } from 'src/app/models/income';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { AuthService } from 'src/app/auth/auth.service';
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-company-expenses',
  templateUrl: './company-expenses.component.html',
  styleUrls: ['./company-expenses.component.css']
})
export class CompanyExpensesComponent implements OnInit {

 
  companyId:number;
  incomes:Income[]=null;
  subTotal:number=null;
  searchText:string;
  error:string=null;
  isLoading:boolean=true;
  incomeByCompany: Subscription
  constructor(private dataStorageService:DataStorageService , private auth:AuthService) { }

  ngOnInit() {
    this.auth.user.subscribe(user=>{
      if(user.id!=null){
        
          this.dataStorageService.incomeByCompany().subscribe(res=>{
          console.log(res);
          this.error=null;
           this.incomes=res;
           this.totalSumExpenses(this.incomes);
           this.isLoading=false;
         },errorMessage=>{
           this.error=errorMessage;
           this.incomes=null;
           console.log(errorMessage);
         })
      }
    })
  }

totalSumExpenses(income:Income[]){
  if(income!=null){
    var total=0;
    for(let i in income){
      total = total+(income[i].amount)
    }
    return this.subTotal=total;
    }
  }

  // ngOnDestroy(): void {
  //   this.incomeByCompany.unsubscribe();
  // }
}
