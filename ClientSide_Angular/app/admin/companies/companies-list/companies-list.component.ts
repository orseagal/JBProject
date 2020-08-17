import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company';
import { DataStorageService } from 'src/app/shared/data-storage.service';

@Component({
  selector: 'app-companies-list',
  templateUrl: './companies-list.component.html',
  styleUrls: ['./companies-list.component.css']
})
export class CompaniesListComponent implements OnInit {
  isLoading:boolean=true;
  companies:Company[]=null;
  error:string=null;
  constructor(private dataStorageService: DataStorageService ) { }

  ngOnInit() {
    this.fetchCompanies();
  }

  fetchCompanies(){
    this.dataStorageService.fetchCompanies().subscribe(companies=>{
      this.companies= companies;
      this.isLoading=false;
    },errorMessage=>{
      this.error=errorMessage;
      console.log(errorMessage);
    });
     
  }
}
