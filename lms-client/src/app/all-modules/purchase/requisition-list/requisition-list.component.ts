import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { environment } from 'src/environments/environment';
import { RequisitionService } from '../service/requisition.service';
import { CreateRequisitionComponent } from '../create-requisition/create-requisition.component';
import { ToastrService } from 'ngx-toastr';
import { NavigationExtras, Router } from '@angular/router';
import { RequisitionSharedService } from '../service/requisition-shared.service';
declare const $: any;
@Component({
  selector: 'app-requisition-list',
  templateUrl: './requisition-list.component.html',
  styleUrls: ['./requisition-list.component.css']
})
export class RequisitionListComponent implements OnInit {
  id: number;
  selectedItem: any;

  @ViewChild('requisitionGridListTable', { static: true }) requisitionGridListTable: any;
  requisitionList: any;
  requisitionListObj: any;

  public baseUrl = environment.baseUrl;
  public listData: any = [];
  // For Modal
  bsModalRef: BsModalRef;
  spinnerService: any;
  public inputForm: FormGroup;
  public configPgn: any;


  constructor(
  private requisitionService: RequisitionService,
  private modalService: BsModalService,
  private formBuilder: FormBuilder,
  private toastr: ToastrService,
  private router: Router,
  private requisitionSharedService: RequisitionSharedService
) { 
  this.configPgn = {
      // my props
      title: '',
      pageNum: 1,
      pageSize: 10,
      totalItem: 50,
      pageSizes: [3, 5, 10, 25, 50, 100, 200, 500, 1000],
      pgnDiplayLastSeq: 10,
      // ngx plugin props
      itemsPerPage: 10,
      currentPage: 1,
      totalItems: 50,
      presentStatus: 'Draft'
  };
}

  ngOnInit(): void {
    this.requisitionlistGrid();
    // this.loadAllRequisition();
    this.initializeForm();
  }

  
  requisitionlistGrid() {
    let that = this;
    this.requisitionList = $(this.requisitionGridListTable.nativeElement);
    this.requisitionListObj = this.requisitionList.DataTable({
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
        url: environment.baseUrl+'/purchase/requisition/get-all',
        type: 'GET',
        data: function (sendData: any) {
          sendData.presentStatus = 'Draft';
        },
        dataSrc: function (response) {
          response.draw = response.draw;
          response.recordsTotal = response.obj.totalElements;
          response.recordsFiltered = response.obj.totalElements;
          response.totalElements = response.obj.totalElements;
          response.pageLength = response.obj.totalPages;
          response.pageNumber = response.obj.number;
          console.log("response: ", response);
          return response.obj.content;

        },
        error: function (request) {
          console.log('request.responseText', request.responseText);
        }
      },
      'order': [[0, 'desc']],
      columns: [
        {
          title: 'Requisition No.',
          data: 'requisitionNo',
          name: 'requisitionNo',
        },
        {
          title: 'Date',
          data: 'reqDate',
        },
        {
          title: 'Created By',
          data:  'createdBy',
        },
        {
          title: 'Status',
          data: 'presentStatus',
        },
        {
          title: 'Broker Name',
          data: 'brokerName',
        },
        {
          title: 'Action',
          className: 'text-center',
          render: function (data, type, row) {
            return '<td> <a class="btn btn-sm btn-primary viewProduct"><i style="color: #fff;" class="fa fa-eye m-r-5"></i></a> &nbsp; <a class="btn btn-sm btn-info editProduct" ><i style="color: #fff;" class="fa fa-pencil m-r-5"></i></a>&nbsp;&nbsp;'+
            '<a class="btn btn-sm btn-danger deleteProduct"> <i style="color: #fff;" class="fa fa-trash-o m-r-5"></i></a></td>';
            // return '<button class="btn-danger deleteProduct"><i class="fas fa-trash-alt"></i> Delete</button>';
          }
        }
      ],
      responsive: true,
      select: true,
      rowCallback: (row: Node, data: any[] | Object) => {
        const self = this;
        console.log(data);
        $('td', row).off('click');
        $('td', row).on('click', () => {
          // self.selectedItem = data;
          // console.log("Selected Patient ", self.selectedItem);
        });
        $(row).find('.deleteProduct').click(function () {
          self.selectedItem = data;
          console.log("Selected Patient ", self.selectedItem);
          that.onDelete();
        });
        // $(row).on('dblclick', () => {
        //   this.edit();
        // });
        $(row).find('.editProduct').click(function () {
          self.selectedItem = data;
          console.log("Selected Patient ", self.selectedItem);
          that.edit();
        });
        $(row).find('.viewProduct').click(function () {
          self.selectedItem = data;
          console.log("Selected Patient ", self.selectedItem);
          that.view();
        });
        return row;
      }
    });
  }
  view() {
    this.requisitionSharedService.setObj(this.selectedItem);
    this.router.navigate(['/purchase/requisitionview']);
  }

  onDelete(){
    $("#delete_entity").modal("show");
  }

  confirmDelete(){
    this.requisitionService.sendDeleteRequest(this.selectedItem.id).subscribe(
      (response: any) => {
        console.log(response);
        // this.spinnerService.hide();  
        $("#delete_entity").modal("hide");
        this.toastr.success("Successfully item is deleted", "Success");
        window.location.reload();
        // this.getListData();
      },
      (error) => {
        console.log(error);
        // this.spinnerService.hide();  
      }
    );
  }

  edit() {
    this.requisitionSharedService.setObj(this.selectedItem);
    this.router.navigate(['/purchase/createrequisition/edit']);
  }

  initializeForm(){
    this.inputForm = this.formBuilder.group({
      creatorName: ["", [Validators.required]],
      executeDate: ["", [Validators.required]],
    });
  }

  searchReq(){

  }

  loadAllRequisition() {
    let queryParams: any = {};
    let apiURL = this.baseUrl + "/purchase/requisition/get-all";
    queryParams.presentStatus = "";
    const params = this.getRequestParams(this.configPgn.title, this.configPgn.pageNum, this.configPgn.pageSize,this.configPgn.presentStatus);
    queryParams = params;

    this.requisitionService.sendGetRequest(apiURL,queryParams).subscribe((response:any) =>{
      console.log("inside the get all requisition by status");
      console.log(response);
      this.listData = response.content;
      this.configPgn.totalItem = response.totalItems;
      this.configPgn.totalItems = response.totalItems;
      console.log(this.listData)
      this.setDisplayLastSequence();
    })
  }

  // pagination handling methods start -----------------------------------------------------------------------
  getRequestParams(searchTitle: string, page: number, pageSize: number, presentStatus: string): any {
    let params: any = {};
    if (searchTitle) {
      params[`title`] = searchTitle;
    }
    if (page) {
      params[`page`] = page - 1;
    }
    if (pageSize) {
      params[`size`] = pageSize;
    }
    if (presentStatus){
      params['presentStatus'] = presentStatus;
    }
    return params;
  }

  setDisplayLastSequence(){
    this.configPgn.pngDiplayLastSeq = (((this.configPgn.pageNum - 1 ) * this.configPgn.pageSize) + this.configPgn.pageSize);
    if(this.listData.length < this.configPgn.pageSize){
      this.configPgn.pngDiplayLastSeq = (((this.configPgn.pageNum - 1 ) * this.configPgn.pageSize) + this.configPgn.pageSize);
    }
    if(this.configPgn.totalItem < this.configPgn.pngDiplayLastSeq){
      this.configPgn.pngDiplayLastSeq = this.configPgn.totalItem;
    }
  }
  handlePageChange(event: number){
    this.configPgn.pageNum = event;
    // set for ngx
    this.configPgn.currentPage = this.configPgn.pageNum;
    this.loadAllRequisition();
  }
  handlePageSizeChange(event: any): void {
    this.configPgn.pageSize = event.target.value;
    this.configPgn.pageNum = 1;
    // set for ngx
    this.configPgn.itemsPerPage = this.configPgn.pageSize;
    this.loadAllRequisition();
  }
// pagination handling methods end -------------------------------------------------------------------------

// ngOnDestroy(): void {
//   this.requisitionSharedService.setId(0);
// }

}
