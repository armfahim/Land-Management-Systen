import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Requisition } from '../../model/requisition';
import { RequisitionSharedService } from '../../service/requisition-shared.service';

@Component({
  selector: 'app-view-requisition',
  templateUrl: './view-requisition.component.html',
  styleUrls: ['./view-requisition.component.css']
})
export class ViewRequisitionComponent implements OnInit {

  subscription: Subscription;
  reqObj: Requisition = new Requisition();

  constructor(
    private requisitionSharedService: RequisitionSharedService
  ) { }

  ngOnInit(): void {
    this.subscription = this.requisitionSharedService.objSource$.subscribe((obj: any) => {
      if (obj) {
        this.reqObj = obj;
        console.log(this.reqObj);
      }
    });
  }

}
