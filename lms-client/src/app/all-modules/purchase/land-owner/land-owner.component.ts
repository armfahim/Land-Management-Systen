import { Component, OnInit, ViewChild } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { environment } from 'src/environments/environment';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { LandOwnerAddEditComponent } from './land-owner-add-edit/land-owner-add-edit.component';
import { LandOwnerService } from '../service/land-owner.service';
import { LandOwner } from '../model/land-owner';
import { RequisitionSharedService } from '../service/requisition-shared.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-land-owner',
  templateUrl: './land-owner.component.html',
  styleUrls: ['./land-owner.component.css']
})
export class LandOwnerComponent implements OnInit {
  id: number;
  selectedItem: any;

  @ViewChild('landOwnerGridListTable', { static: true }) landOwnerGridListTable: any;
  landOwnerList: any;
  landOwnerListObj: any;
  dataParam: any = {};

  // For Modal
  bsModalRef: BsModalRef;

  landOwner: LandOwner = new LandOwner();

  reqId: number;
  subscription: Subscription;

  constructor(
    private landOwnerService: LandOwnerService,
    private modalService: BsModalService,
    private toastr: ToastrService,
    private requisitionSharedService: RequisitionSharedService,
  ) { }

  ngOnInit() {
    this.landOwnerlistGrid();
    //Id From Parent (Worklist) by Shared Service
    this.subscription = this.requisitionSharedService.id$.subscribe((id: any) => {
      if (id) {
        this.reqId = id;
        console.log("Req Id Genarate to Land Owner After Save : ", this.reqId);
      }
    });
  }

  landOwnerlistGrid() {
    let that = this;
    this.landOwnerList = $(this.landOwnerGridListTable.nativeElement);
    this.landOwnerListObj = this.landOwnerList.DataTable({
      pagingType: 'full_numbers',
      pageLength: 10,
      serverSide: true,
      processing: true,
      ajax: {
        beforeSend: function (xhr) {
          // TODO: Need to change bearer token from static to dynamic
          xhr.setRequestHeader('Content-Type', 'application/json');
          xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem("token"));
        },
        url: environment.baseUrl + '/api/land-owner/grid-list',
        type: 'GET',
        data: function (sendData: any) {
          sendData.requisitionId = that.reqId;
        },
        dataSrc: function (response) {
          if(response.obj != null) {
            response.recordsTotal = response.obj.totalElements;
            response.recordsFiltered = response.obj.totalElements;
            console.log("Grid List Response: ", response);
            return response.obj.content;
          } else {
            return response.obj = null;
          }
        },
        // success: function (response) {
        //   var dataArray = $.map(response, function (item) {
        //     return item;
        //   });
        //   alert(dataArray);
        //   console.log(dataArray);
        // },
        error: function (request) {
          console.log('request.responseText', request.responseText);
        }
      },
      'order': [[0, 'desc']],
      columns: [
        {
          title: 'SL',
          data: 'id',
          render: function (data, type, row, meta) {
            return '<span>' + (meta.row + 1) + '</span>';
          }
        },
        {
          title: 'Land Owner Name',
          data: 'ownerName',
          name: 'ownerName',
        },
        // {
        //   title: 'Date of Birth',
        //   data: 'dateOfBirth',
        //   render: (data) => {
        //     return moment(data).format("DD/MM/YYYY")
        //   }
        // },
        {
          title: 'Age',
          data: 'age',
        },
        {
          title: 'Owned Land',
          data: 'ownedLand',
        },
        {
          title: 'Attorney',
          data: 'attorney',
        },
        {
          title: 'NID',
          data: 'nid',
        },
        {
          title: 'Ownership Type',
          data: 'ownershipType',
        },
        {
          title: 'Action',
          className: 'text-center',
          render: function (data, type, row) {
            return '<button class="btn btn-danger deleting"><i class="fas fa-trash-alt"></i> Delete</button>';
          }
        }
      ],
      responsive: true,
      select: true,
      rowCallback: (row: Node, data: any[] | Object) => {
        const self = this;
        $('td', row).off('click');
        $('td', row).on('click', () => {
          self.selectedItem = data;
          console.log("Selected Items: ", self.selectedItem);
        });
        $(row).find('.deleting').click(function () {
          that.onDelete(data);
        });
        $(row).on('dblclick', () => {
          this.edit();
        });
        return row;
      }
    });
  }

  add(): void {
    const initialState = {
      reqId: this.reqId,
      title: 'Add Land Owners Info'
    }
    this.bsModalRef = this.modalService.show(LandOwnerAddEditComponent, { class: 'modal-md', initialState, backdrop: 'static' });
    this.bsModalRef.content.onClose.subscribe(result => {
      if (result == true) {
        this.landOwnerListObj.draw();
      }
    });
  }

  edit(): void {
    if (this.selectedItem) {
      // this.selectedItem.invoiceDate = this.selectedItem.invoiceDate ? moment(new Date(this.selectedItem.invoiceDate)).format('DD-MM-YYYY') : null;
      const initialState = {
        reqId: this.reqId,
        landOwnerObj: this.selectedItem,
        title: 'Update Land Owners Info'
      }
      this.bsModalRef = this.modalService.show(LandOwnerAddEditComponent, { class: 'modal-md', initialState, backdrop: 'static' });
      this.bsModalRef.content.onClose.subscribe(result => {
        if (result == true) {
          this.landOwnerListObj.draw();
          this.selectedItem = null;
        }
      });
    } else {
      this.toastr.warning('', "Please select an Item")
    }
  }

  onDelete(selectedItem) {
    console.log('Selected Patient for Delete', selectedItem);
    if (selectedItem) {
      this.landOwnerService.onDelete(selectedItem.id).subscribe(
        () => {
          this.toastr.success('', "Data Successfully Deleted")
          this.selectedItem = null;
          this.landOwnerListObj.draw();
        },
        () => {
          this.toastr.warning('', "Data Not Deleting, Please Check")
        }
      )
    }
  }

  ngOnDestroy(): void {
    this.requisitionSharedService.setId(0);
  }

}
