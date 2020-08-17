import { NgModule } from "@angular/core";
import{Routes, RouterModule} from "@angular/router";
import { ProductsComponent } from "./products/products.component";
import { AboutUsComponent } from "./about-us/about-us.component";
import { AuthComponent } from "./auth/auth.component";
import { PageNotFoundComponent } from "./page-not-found/page-not-found.component";
import { SignUpComponent } from "./auth/sign-up/sign-up.component";
import { ContactComponent } from './contact/contact.component';
import { AdminComponent } from './admin/admin.component';
import { EditCustomersComponent } from './admin/customers/edit-customers/edit-customers.component';
import { CreateCustomersComponent } from './admin/customers/create-customers/create-customers.component';
import { EditCompaniesComponent } from './admin/companies/edit-companies/edit-companies.component';
import { CustomersListComponent } from './admin/customers/customers-list/customers-list.component';
import { CompaniesListComponent } from './admin/companies/companies-list/companies-list.component';
import { CreateCompaniesComponent } from './admin/companies/create-companies/create-companies.component';
import { AdminAuthGuard } from './admin/admin.auth-guard';
import { CustomerComponent } from './customer/customer.component';
import { CustomerProfileComponent } from './customer/profile/customer-profile.component';
import { CustomerPasswordChangeComponent } from './customer/password-change/customer-password-change.component';
import { CompanyComponent } from './company/company.component';
import { CompanyPasswordChangeComponent } from './company/company-password-change/company-password-change.component';
import { CompanyProfileComponent } from './company/company-profile/company-profile.component';
import { CreateCouponComponent } from './company/create-coupon/create-coupon.component';
import { CompanyCouponsComponent } from './company/company-coupons/company-coupons.component';
import { SearchComponent } from './products/search/search.component';
import { CompanyAuthGuard } from './company/company.auth-guard';
import { CustomerAuthGuard } from './customer/customer.auth-guard';
import { CanDeactivateGuard } from './shared/can-deactivate-guard.service';
import { ProductDetailsComponent } from './products/product-details/product-details.component';
import { ProductsListComponent } from './products/products-list/products-list.component';
import { ProductsCategoryComponent } from './products/products-category/products-category.component';
import { CartComponent } from './cart/cart.component';
import { PurchseHistoryComponent } from './customer/purchse-history/purchse-history.component';
import { CompanyCouponsEditComponent } from './company/company-coupons-edit/company-coupons-edit.component';
import { AllIncomeComponent } from './admin/finance/all-income/all-income.component';
import { IncomeByCustomerComponent } from './admin/finance/income-by-customer/income-by-customer.component';
import { IncomeByCompanyComponent } from './admin/finance/income-by-company/income-by-company.component';
import { CompanyExpensesComponent } from './company/finance/company-expenses/company-expenses.component';
import { SoldCouponsComponent } from './company/finance/sold-coupons/sold-coupons.component';



const appRoutes: Routes=[

{path: '' , redirectTo:'/products/products', pathMatch:'full'},
{path: 'products' , redirectTo:'/products/products', pathMatch:'full'},
{path: 'products' ,component: ProductsComponent ,children:[
{path: 'products', component: ProductsListComponent},
{path: 'category/:category', component: ProductsCategoryComponent},
{path: 'products/:id' ,component: ProductDetailsComponent}
]},

{path: 'login' , component:AuthComponent},
{path: 'signup' , component:SignUpComponent},
{path: 'about-us' , component: AboutUsComponent},
{path: 'contact' , component: ContactComponent},
{path: 'search' , component: SearchComponent},
{path:'cart',component: CartComponent},
{path: 'page-not-found', component:PageNotFoundComponent},


//----Admin----
{path: 'admin' , component: AdminComponent,canActivate:[AdminAuthGuard],children:[
    {path:'customers-list' , component: CustomersListComponent},
    {path: 'create-customers', component: CreateCustomersComponent},
    {path: 'edit-customers', component: EditCustomersComponent},

    {path: 'companies-list', component: CompaniesListComponent},
    {path:'create-companies', component: CreateCompaniesComponent},
    {path: 'edit-companies', component: EditCompaniesComponent},

    {path:'all-income', component:AllIncomeComponent},
    {path:'incomeByCustomer', component:IncomeByCustomerComponent},
    {path:'incomeByCompany', component:IncomeByCompanyComponent}
    
]

},
//----Customer----
{path: 'customer' , component: CustomerComponent,canActivate:[CustomerAuthGuard], children:[
{path:'profile', component: CustomerProfileComponent},
{path:'password-change', component:CustomerPasswordChangeComponent},
{path:'purchase-history', component:PurchseHistoryComponent},

]

},
//----Company----
    {path:'company', component: CompanyComponent,canActivate:[CompanyAuthGuard] , children:[
    {path:'profile' ,component: CompanyProfileComponent},
    {path:'password-change', component:CompanyPasswordChangeComponent},
    {path:'create-coupon', component:CreateCouponComponent , canDeactivate:[CanDeactivateGuard]},
    {path:'coupons', component: CompanyCouponsComponent},
    {path:'coupons/:id', component:CompanyCouponsEditComponent},
    {path:'expenses', component:CompanyExpensesComponent},
    {path:'soldCoupons', component:SoldCouponsComponent}
]},

{path: '**',redirectTo:'/products/products' }
];



@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export class AppRoutingModule{


}