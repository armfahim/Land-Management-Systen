import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { ToastrService } from 'ngx-toastr';
import { Subject } from 'rxjs';
import { LandOwner } from '../../model/land-owner';
import { LandOwnerService } from '../../service/land-owner.service';

@Component({
  selector: 'app-land-owner-add-edit',
  templateUrl: './land-owner-add-edit.component.html',
  styleUrls: ['./land-owner-add-edit.component.css']
})
export class LandOwnerAddEditComponent implements OnInit {
  onClose: Subject<boolean>;
  landOwnerObj: LandOwner = new LandOwner();
  reqId: number;

  title: any = '';

  constructor(
    public bsModalRef: BsModalRef,
    private toastr: ToastrService,
    private landOwnerService: LandOwnerService,
  ) { }

  ngOnInit() {
    console.log('Selected Obj: ', this.landOwnerObj);
    console.log('ID: ', this.reqId);
    this.onClose = new Subject();
  }

  saveOrUpdate(): void {
    if (this.landOwnerObj.id != null || this.landOwnerObj.id != undefined) {
      this.update();
    } else {
      this.save();
    }
  }

  save(): void {
    // this.landOwnerObj.invoiceDate = moment(new Date(this.landOwnerObj.invoiceDate)).toDate();
    let formData: any = {};
    formData = this.landOwnerObj;
    formData = Object.assign(this.landOwnerObj, {
      requisition: { id: this.reqId },
    });
    
    if (this.landOwnerObj.ownerName != null) {
      this.landOwnerService.onSaveItem(formData).subscribe(
        res => {
          if (res) {
            this.toastr.success('', "Data Successfully Saved");
            this.bsModalRef.hide();
            this.onClose.next(true);
          } else {
            this.toastr.warning('', "Error Occured");
            this.onClose.next(true);
          }
        }, err => {
          console.error('Error occured when save ', err);
          this.toastr.error('Error occured when save', err.message);
          this.bsModalRef.hide();
          this.onClose.next(true);
        }
      );
    } else {
      this.toastr.warning('', "Please Insert Data to All Mendatory Field's...");
    }

  }

  update(): void {
    // this.landOwnerObj.invoiceDate = moment(new Date(this.landOwnerObj.invoiceDate)).toDate();
    let formData: any = {};
    formData = this.landOwnerObj;
    formData = Object.assign(this.landOwnerObj, {
      requisition: { id: this.reqId },
    });

    if (this.landOwnerObj.ownerName != null) {
      this.landOwnerService.onUpdateItem(formData).subscribe(
        res => {
          if (res) {
            this.toastr.success('', "Data Successfully Updated");
            this.bsModalRef.hide();
            this.onClose.next(true);
          } else {
            this.toastr.warning('', "Error Occured");
            this.onClose.next(false);
          }
        }, err => {
          console.error('Error occured when Update', err);
          this.toastr.error('Error occured when Update', err.message);
          this.bsModalRef.hide();
          this.onClose.next(true);
        }
      );
    } else {
      this.toastr.warning('', "Please Insert Data to All Field's...");
    }
  }
}
