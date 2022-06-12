import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PurchaseComponent } from './purchase.component';
import { CreateRequisitionComponent } from './create-requisition/create-requisition.component';
import { RequisitionListComponent } from './requisition-list/requisition-list.component';
import { LandOwnerComponent } from './land-owner/land-owner.component';
import { ViewRequisitionComponent } from './create-requisition/view-requisition/view-requisition.component';

const routes: Routes = [
  {
    path: '',
    component: PurchaseComponent,
    children: [
      {
        path: 'createrequisition',
        component: CreateRequisitionComponent
      }, 
      {
        path: 'requisitionlist',
        component: RequisitionListComponent
      },
      {
        path: 'land-owner',
        component: LandOwnerComponent
      },
      {
        path:"createrequisition/edit",
        component:CreateRequisitionComponent
      },
      {
        path:"requisitionview",
        component:ViewRequisitionComponent
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CreateRequistionRoutingModule { }
