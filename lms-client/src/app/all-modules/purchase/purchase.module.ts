import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CreateRequistionRoutingModule } from './purchase-routing.module';
import { PurchaseComponent } from './purchase.component';
import { CreateRequisitionComponent } from './create-requisition/create-requisition.component';
import { DataTablesModule } from 'angular-datatables';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { SharingModule } from 'src/app/sharing/sharing.module';
import { PickListModule } from 'primeng/picklist';
import {NgSelectModule, NgOption} from '@ng-select/ng-select';
import { HttpClientModule } from '@angular/common/http';
import { NgxSpinnerModule } from 'ngx-spinner';
import { AppComponent } from 'src/app/app.component';
import { RequisitionListComponent } from './requisition-list/requisition-list.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { LandOwnerComponent } from './land-owner/land-owner.component';
import { LandOwnerAddEditComponent } from './land-owner/land-owner-add-edit/land-owner-add-edit.component';
import { ViewRequisitionComponent } from './create-requisition/view-requisition/view-requisition.component';

@NgModule({
  declarations: [PurchaseComponent,CreateRequisitionComponent, RequisitionListComponent, LandOwnerComponent, LandOwnerAddEditComponent, ViewRequisitionComponent],
  imports: [
    CommonModule,
    FormsModule,
    SharingModule,
    ReactiveFormsModule,
    PickListModule,
    CreateRequistionRoutingModule, PickListModule,
    BsDatepickerModule.forRoot(),
    DataTablesModule,
    NgSelectModule,
    FormsModule,
    HttpClientModule,
    NgxPaginationModule,
    NgxSpinnerModule,
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    LandOwnerAddEditComponent
  ]
})

export class PurchaseModule { }
