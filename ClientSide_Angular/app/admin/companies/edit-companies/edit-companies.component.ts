import { Component, OnInit, TemplateRef } from '@angular/core';
import { Company } from 'src/app/models/company';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Customer } from 'src/app/models/customer';

@Component({
  selector: 'app-edit-companies',
  templateUrl: './edit-companies.component.html',
  styleUrls: ['./edit-companies.component.css']
})
export class EditCompaniesComponent implements OnInit {
  companies:Company[]=null;
  modalRef: BsModalRef;
  message: string;
  constructor(private dataStorageService: DataStorageService ,private modalService: BsModalService) { }

  ngOnInit() {

    this.dataStorageService.fetchCompanies().subscribe(companies=>{
      this.companies=companies;

    })
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {class: 'modal-sm'});
  }
 
  confirm(company:Company): void {
    this.onDelete(company);
    this.modalRef.hide();
  }
 
  decline(): void {
    this.modalRef.hide();
  }

  onDelete(company:Company){
    // var isDelete = confirm("Are You Sure You Want To Delete Customer " +customer.name +" ?");
    
      console.log("delete")
      this.dataStorageService.deleteCompany(company.id).subscribe(result=>{
        this.fetchCompanies();
        console.log("customer deleted")
      })
    
  }
  
  
  fetchCompanies(){
    this.dataStorageService.fetchCompanies().subscribe(companies=>{
      this.companies= companies;
    });
     
  }
}
