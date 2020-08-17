import { BrowserModule } from '@angular/platform-browser';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { ProductsComponent } from './products/products.component';
import { AppRoutingModule } from './app-routing.module';
import { AboutUsComponent } from './about-us/about-us.component';
import { AuthComponent } from './auth/auth.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import { DropDownDirective } from './shared/drop-down.directive';
import { LoadingSpinnerComponent } from './shared/loading-spinner/loading-spinner.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ContactComponent } from './contact/contact.component';
import { AdminComponent } from './admin/admin.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import {MatExpansionModule} from '@angular/material/expansion';
import { ModalModule } from 'ngx-bootstrap/modal';
import { ConfirmDialogComponent } from './shared/confirm-dialog/confirm-dialog.component';
import { EditCustomersComponent } from './admin/customers/edit-customers/edit-customers.component';
import { CreateCustomersComponent } from './admin/customers/create-customers/create-customers.component';
import { EditCompaniesComponent } from './admin/companies/edit-companies/edit-companies.component';
import { CreateCompaniesComponent } from './admin/companies/create-companies/create-companies.component';
import { CustomersListComponent } from './admin/customers/customers-list/customers-list.component';
import { CompaniesListComponent } from './admin/companies/companies-list/companies-list.component';
import { CustomerComponent } from './customer/customer.component';
import { CustomerProfileComponent } from './customer/profile/customer-profile.component';
import { CustomerPasswordChangeComponent } from './customer/password-change/customer-password-change.component';
import { CompanyComponent } from './company/company.component';
import { CompanyPasswordChangeComponent } from './company/company-password-change/company-password-change.component';
import { CompanyProfileComponent } from './company/company-profile/company-profile.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatFormFieldModule} from '@angular/material/form-field';
import { NotificationDialogComponent } from './shared/notification-dialog/notification-dialog.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NotificationDialogService } from './shared/notification-dialog/notification-dialog.service';
import { CompanyCouponsComponent } from './company/company-coupons/company-coupons.component';
import { CreateCouponComponent } from './company/create-coupon/create-coupon.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { DatepickerPopupComponent } from './shared/datepicker-popup/datepicker-popup.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {A11yModule} from '@angular/cdk/a11y';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {PortalModule} from '@angular/cdk/portal';
import {ScrollingModule} from '@angular/cdk/scrolling';
import {CdkStepperModule} from '@angular/cdk/stepper';
import {CdkTableModule} from '@angular/cdk/table';
import {CdkTreeModule} from '@angular/cdk/tree';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatBadgeModule} from '@angular/material/badge';
import {MatBottomSheetModule} from '@angular/material/bottom-sheet';

import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatCardModule} from '@angular/material/card';

import {MatChipsModule} from '@angular/material/chips';
import {MatStepperModule} from '@angular/material/stepper';

import {MatDialogModule} from '@angular/material/dialog';
import {MatDividerModule} from '@angular/material/divider';

import {MatInputModule} from '@angular/material/input';

import {MatMenuModule} from '@angular/material/menu';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';

import {MatSliderModule} from '@angular/material/slider';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatSortModule} from '@angular/material/sort';
import {MatTableModule} from '@angular/material/table';
import {MatTabsModule} from '@angular/material/tabs';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatTreeModule} from '@angular/material/tree';
import {AngularFireModule} from '@angular/fire';
import {AngularFireStorageModule} from '@angular/fire/storage';
import {AngularFireDatabaseModule} from '@angular/fire/database';
import {environment} from "../environments/environment";
import { DialogOverviewDialog } from './shared/mat-dialog/mat-dialog';
import { SearchComponent } from './products/search/search.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatConfirmDialogComponent } from './shared/mat-confirm-dialog/mat-confirm-dialog.component';
import { ProductDetailsComponent } from './products/product-details/product-details.component';
import { ProductsListComponent } from './products/products-list/products-list.component';
import { ProductsCategoryComponent } from './products/products-category/products-category.component';
import { CartComponent } from './cart/cart.component';
import { PurchseHistoryComponent } from './customer/purchse-history/purchse-history.component';
import { BasicAuthHttpInterceptorService } from './shared/basic-auth-http-interceptor.service';
import { CompanyCouponsEditComponent } from './company/company-coupons-edit/company-coupons-edit.component';
import { IncomeByCompanyComponent } from './admin/finance/income-by-company/income-by-company.component';
import { IncomeByCustomerComponent } from './admin/finance/income-by-customer/income-by-customer.component';
import { CompanyExpensesComponent } from './company/finance/company-expenses/company-expenses.component';
import { SoldCouponsComponent } from './company/finance/sold-coupons/sold-coupons.component';
import { AllIncomeComponent } from './admin/finance/all-income/all-income.component';





@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ProductsComponent,
    AboutUsComponent,
    AuthComponent,
    PageNotFoundComponent,
    SignUpComponent,
    DropDownDirective,
    LoadingSpinnerComponent,
    ContactComponent,
    AdminComponent,
    ConfirmDialogComponent,
    EditCustomersComponent,
    CreateCustomersComponent,
    EditCompaniesComponent,
    CreateCompaniesComponent,
    CustomersListComponent,
    CompaniesListComponent,
    CustomerComponent,
    CustomerProfileComponent,
    CustomerPasswordChangeComponent,
    CompanyComponent,
    CompanyPasswordChangeComponent,
    CompanyProfileComponent,
    NotificationDialogComponent,
    CompanyCouponsComponent,
    CreateCouponComponent,
    DatepickerPopupComponent,
    DialogOverviewDialog,
    SearchComponent,
    MatConfirmDialogComponent,
    ProductDetailsComponent,
    ProductsListComponent,
    ProductsCategoryComponent,
    CartComponent,
    PurchseHistoryComponent,
    CompanyCouponsEditComponent,
    AllIncomeComponent,
    IncomeByCompanyComponent,
    IncomeByCustomerComponent,
    CompanyExpensesComponent,
    SoldCouponsComponent,

    
  
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    AngularFontAwesomeModule,
    MatExpansionModule,
    MatFormFieldModule,
    ModalModule.forRoot(),
    NgbModule,
    MatDatepickerModule,
    A11yModule,
    CdkStepperModule,
    CdkTableModule,
    CdkTreeModule,
    DragDropModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatTreeModule,
    PortalModule,
    ScrollingModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireStorageModule,
    AngularFireDatabaseModule,
    Ng2SearchPipeModule,
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory })
  ],

  providers: [{
    provide:HTTP_INTERCEPTORS, useClass:BasicAuthHttpInterceptorService, multi:true
  }],
  entryComponents: [ NotificationDialogComponent, DialogOverviewDialog,MatConfirmDialogComponent ],
  bootstrap: [AppComponent]
})
export class AppModule { }
