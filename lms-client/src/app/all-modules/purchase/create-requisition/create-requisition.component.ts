import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DataTableDirective } from 'angular-datatables';
import { ToastrService } from 'ngx-toastr';
import { NgxSpinnerService } from "ngx-spinner";
import { Subject, Subscription } from 'rxjs';
import { first } from 'rxjs/operators';
import { AllModulesService } from '../../all-modules.service';
import { RequisitionService } from '../service/requisition.service';
import { Divisions } from '../model/divisions';
import { Districts } from '../model/districts';
import { Thanas } from '../model/thana';
import { environment } from 'src/environments/environment';
import { Mouza } from '../model/mouza';
import { LandType } from '../model/land-type';
import { DagInfo } from '../model/dag-info';
import { Requisition } from '../model/requisition';
import { DivisionService } from '../service/division.service';
import { DistrictService } from '../service/district.service';
import { ThanaService } from '../service/thana.service';
import { MouzaService } from '../service/mouza.service';
import { LandTypeService } from '../service/land-type.service';
import { DagInfoService } from '../service/dag-info.service';
import { KhatianType } from '../model/Khatian-type';
import { KhatianTypeService } from '../service/khatian-type.service';
import { RequisitionSharedService } from '../service/requisition-shared.service';

declare const $: any;
@Component({
  selector: 'app-create-requisition',
  templateUrl: './create-requisition.component.html',
  styleUrls: ['./create-requisition.component.css'],
  providers: [DatePipe]
})
export class CreateRequisitionComponent implements OnInit {

  submitted = false;
  public addOwnerForm: FormGroup;
  public createRequisitionForm: FormGroup;
  loading = false;
  public url: any = "createrequisition";
  isAddMode!: boolean;
  id!: number;
  public baseUrl = environment.baseUrl;

  //All Address
  public divisions: Divisions[];
  public districts: any;
  public thanas: any;
  public mouzas: any;
  public khatianTypes: any;
  public landTypes: any;
  public dagInfos: any;
  public saleMedia = [];

  //obj declarations
  districtObj: Districts = new Districts();
  divisionObj: Divisions = new Divisions();
  thanaObj: Thanas = new Thanas();
  mouzaObj: Mouza = new Mouza();
  khatianTypeObj: KhatianType = new KhatianType;
  landTypeObj: LandType = new LandType();
  dagInfoObj: DagInfo = new DagInfo();
  reqObj: Requisition = new Requisition();

  today = new Date();

  @ViewChild(DataTableDirective, { static: false })
  public dtElement: DataTableDirective;

  public dtTrigger: Subject<any> = new Subject();
  // spinnerService: any;

  // Necessary variable declarations
  recordQty;
  govtLandAcq;
  restLandAfterAcq: number = null;
  totalPreBougght;
  availableLand;

  reqTab: any[] = [
    { title: 'Requisition Info', content: 'requisitionInfo', initiated: true, active: true },
    { title: 'Land Owners Info', content: 'landOwnerInfo', initiated: true, active: false }
  ];

  reqId: number;
  subscription: Subscription;

  constructor(
    private datePipe: DatePipe,
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private router: Router,
    private route: ActivatedRoute,
    private spinnerService: NgxSpinnerService,
    private requisitionService: RequisitionService,
    private srvModuleService: AllModulesService,
    private divisionService: DivisionService,
    private districtService: DistrictService,
    private thanaService: ThanaService,
    private mouzaService: MouzaService,
    private khatianTypeService: KhatianTypeService,
    private landTypeService: LandTypeService,
    private dagInfoService: DagInfoService,
    private requisitionSharedService: RequisitionSharedService,
  ) { }

  ngOnInit() {
    // this.id = this.route.snapshot.params["id"];  
    this.subscription = this.requisitionSharedService.objSource$.subscribe((obj: any) => {
      if (obj) {
        this.reqObj = obj;
        console.log(this.reqObj);
        console.log(this.divisions);

        // this.reqObj = Object.assign(obj.value, {
        //   division: { id: this.reqObj.division },
        //   district: { id: this.reqObj.district },
        //   thana: { id: this.reqObj.thana},
        //   mouza: { id: this.reqObj.mouza },
        //   khatianType: { id: this.reqObj.khatianType },
        //   landType: { id: this.reqObj.landType },
        //   dagInfo: { id: this.reqObj.dagInfo }
        // });
      }
    });

    this.isAddMode = !this.reqObj.id;
    if(this.isAddMode){
      this.initializeSetUp()
    }
    this.initializeForm();
    this.initializeOwnerForm();
  }

  initializeSetUp() {
      this.loadAllDivisions();
      this.loadAllDistrict(null);
      this.loadAllThana(null);
      this.loadAllKhatianType();
      this.loadAllMouza();
      this.loadAllLandType();
      this.loadAllDagInfo();
      this.latestRequisitionNumber();
      this.loadSaleMedia();
  }

  initializeOwnerForm() {
    this.addOwnerForm = this.formBuilder.group({
      name: ["", [Validators.required]],
      dob: ["", [Validators.required]],
      nid: ["", [Validators.required]],
      photo: ["", []],
      poa: ["", []],
      ownedLand: ["", [Validators.required]],
      ownershipType: ["", [Validators.required]],
    });
  }
  initializeForm() {
    this.createRequisitionForm = this.formBuilder.group({
      requisitionNo: ["", [Validators.required]],
      reqDate: ["", [Validators.required]],
      saleMedia: ["", [Validators.required]],
      brokerName: ["", [Validators.required]],
      contactNumber: ["", []],
      brokerNid: ["", []],
      offeringLand: ["", [Validators.required]],
      division: ["", [Validators.required]],
      district: ["", [Validators.required]],
      thana: ["", [Validators.required]],
      mouza: ["", [Validators.required]],
      khatianType: ["", [Validators.required]],
      khatianNo: ["", [Validators.required]],
      dagInfo: ["", [Validators.required]],
      landType: ["", [Validators.required]],
      recordQuantity: ["", [Validators.required]],
      govtLa: ["", [Validators.required]],
      restLandAfterLa: ["", [Validators.required]],
      totalPreBought: ["", [Validators.required]],
      availableLand: ["", [Validators.required]],
      sellingQty: ["", [Validators.required]],
      govtMouzaRate: ["", [Validators.required]],
      offeringPrice: ["", [Validators.required]],
      sourceRemarks: [""],
    })

    // !this.isAddMode
    if (!this.isAddMode) {
      this.requisitionService
          //formatted object type data for updating
          console.log("OK=====>>>");
          console.log(this.reqObj);

          if (this.reqObj.division != null) {  
            let mDivision = this.reqObj.division;
            this.reqObj.division = mDivision;
            this.loadAllDivisions();
          }

          if (this.reqObj.division != null) {  
            let mDivision = this.reqObj.division;
            this.reqObj.division= mDivision;
            this.loadAllDistrict(this.reqObj.division.id);
          }

          if (this.reqObj.district != null) {
            let mDistrict = this.reqObj.district;
            this.reqObj.district.id = mDistrict.id;
            this.loadAllThana(this.reqObj.district.id);
          }

          if (this.reqObj.thana != null) {
            let mThana = this.reqObj.thana;
            this.reqObj.thana.id = mThana.id;
          }
          if (this.reqObj.mouza != null) {
            let mMouza = this.reqObj.mouza;
            this.reqObj.mouza.id = mMouza.id;
            this.loadAllMouza();
          }
          if (this.reqObj.khatianType != null) {
            let mKhatianType = this.reqObj.khatianType;
            this.reqObj.khatianType.id = mKhatianType.id;
            this.loadAllKhatianType();
          }
          if (this.reqObj.landType != null) {
            let mLandType= this.reqObj.landType;
            this.reqObj.landType.id = mLandType.id;
            this.loadAllLandType();
          }
          if (this.reqObj.dagInfo != null) {
            let mDagInfo= this.reqObj.dagInfo;
            this.reqObj.dagInfo.id = mDagInfo.id;
            this.loadAllDagInfo();
          }

          // if (this.reqObj.saleMedia != null) {
          //   let mDagInfo= this.reqObj.saleMedia;
          //   this.reqObj.saleMedia = mDagInfo;
          //   this.loadSaleMedia();
          // }

          
          
          console.log(" Requisition INFO " + this.reqObj);
          console.log(this.reqObj);
          this.spinnerService.show();

          this.createRequisitionForm.patchValue(this.reqObj);
    }
  }

  createRequisitionOnSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.createRequisitionForm.invalid) {
      this.toastr.info("Please insert valid data");
      return;
    }
    console.log(this.isAddMode);
    this.loading = true;
    if (this.isAddMode) {
      this.addRequisition();
    } else {
      this.updateRequisition();
    }
  }

  addRequisition() {
    let formData: any = {};
    formData = this.createRequisitionForm.value;

    formData.reqDate = this.datePipe.transform(formData.reqDate, 'yyyy-MM-dd').toString().slice(0, 10);
    // let convertDob = this.datePipe
    //   .transform(reqDate, "dd-MM-yyyy")
    //   .toString()
    //   .slice(0, 10);

    formData = Object.assign(this.createRequisitionForm.value, {
      division: { id: this.getDivision.value },
      district: { id: this.getDistrict.value },
      thana: { id: this.getThana.value },
      mouza: { id: this.getMouza.value },
      khatianType: { id: this.getKhatianType.value },
      landType: { id: this.getLandType.value },
      dagInfo: { id: this.getDagInfo.value }
    });

    this.spinnerService.show();
    console.log(formData);
    this.requisitionService
      .createRequisition(formData)
      .pipe(first())
      .subscribe(
        (data: any) => {
          console.log(data);
          if (data) {
            console.log("Requisittion Save Success Response : ", data);
            this.reqId = data.id;
            this.requisitionSharedService.setId(this.reqId);
            this.router.navigate(["/purchase/land-owner"], { relativeTo: this.route });
            for (let i in this.reqTab) {
              if (this.reqTab[i].content == 'landOwnerInfo') {
                this.reqTab[i].initiated = true;
                this.reqTab[i].active = true;
              }
            }
          }
          this.spinnerService.hide();
          this.toastr.success("Successfully created requisition");
          window.location.reload();
          this.router.navigate(["/purchase/createrequisition"], { relativeTo: this.route });
        },
        (error) => {
          this.toastr.error("Error in saving requisition ");
        }
      )
      .add(() => (this.loading = false));
  }

  /**
   * Save form data
   */
  updateRequisition() {
    let apiURL = this.baseUrl + "/purchase/requisition/update";
    console.log(this.reqObj);
    this.spinnerService.show();
    this.requisitionService.sendPutRequest(apiURL, this.reqObj).subscribe(
      (response: any) => {
        console.log(response);
        this.spinnerService.hide();
        this.router.navigate(["/purchase/requisitionlist"], { relativeTo: this.route });
      },
      (error) => {
        console.log(error);
        this.spinnerService.hide();
      }
    );
  }

  loadSaleMedia() {
    let apiURL = this.baseUrl + "/purchase/requisition/fetch-sale-media";
    let queryParams: any = {};
    this.requisitionService.sendGetRequest(apiURL, queryParams).subscribe((data: any) => {
      console.log(data);
      this.reqObj.saleMedia = data;
    }, (error) => {
      this.toastr.error(error.error.message);
    });
  }

  latestRequisitionNumber() {
    let apiURL = this.baseUrl + "/purchase/requisition/find-latest-requisition";
    let queryParams: any = {};
    this.requisitionService.sendGetRequest(apiURL, queryParams).subscribe((data: any) => {
      if (data == null) {
        this.reqObj.requisitionNo = 1
        console.log(this.reqObj.requisitionNo);
      } else {
        this.reqObj.requisitionNo = data.requisitionNo + 1;
        console.log(this.reqObj.requisitionNo);
      }

    }, (error) => {
      this.toastr.error(error.error.message);
    });
  }

  loadAllMouza() {
    this.mouzaService.fetchAllMouza().subscribe((data: any) => {
      this.reqObj.mouza = data;
      this.spinnerService.hide();
    }, (error) => {
      this.toastr.error(error.error.message);
      this.spinnerService.hide();
    });
  }

  loadAllDivisions() {
    this.spinnerService.show();
    this.divisionService.fetchAllDivision().subscribe((data: any) => {
      this.reqObj.division = data;
      this.spinnerService.hide();
    }, (error) => {
      this.toastr.error(error.error.message);
      this.spinnerService.hide();
    });
  }

  loadAllDistrict(divisionId) {
    console.log(divisionId);
    if (divisionId == null) {
      this.spinnerService.show();
      this.districtService.fetchAllDistrict().subscribe((data: any) => {
        this.reqObj.district = data;
        this.spinnerService.hide();
      }, (error) => {
        this.toastr.error(error.error.message);
        this.spinnerService.hide();
      });
    } else {
      this.spinnerService.show();
      this.districtService.fetchDistrictByDivId(divisionId).subscribe((data: any) => {
        console.log(data);
        this.reqObj.district = data;
        this.spinnerService.hide();
      }, (error) => {
        this.toastr.error(error.error.message);
        this.spinnerService.hide();
      });
    }

  }

  loadAllThana(id) {
    if (id == null) {
      this.spinnerService.show();
      this.thanaService.fetchAllThana().subscribe((data: any) => {
        this.reqObj.thana = data;
        this.spinnerService.hide();
      }, (error) => {
        this.toastr.error(error.error.message);
        this.spinnerService.hide();
      });
    } else {
      this.spinnerService.show();
      this.thanaService.fetchThanasByDisId(id).subscribe((data: any) => {
        this.reqObj.thana = data;
        this.spinnerService.hide();
      }, (error) => {
        this.toastr.error(error.error.message);
        this.spinnerService.hide();
      });
    }
  }

  loadAllKhatianType() {
    this.spinnerService.show();
    this.khatianTypeService.fetchKhatianType().subscribe((data: any) => {
      this.reqObj.khatianType = data;
    }, (error) => {
      this.toastr.error(error.error.message);
      this.spinnerService.hide();
    });
  }

  loadAllLandType() {
    this.spinnerService.show();
    this.landTypeService.fetchLandType().subscribe((data: any) => {
      this.reqObj.landType = data;
    }, (error) => {
      this.toastr.error(error.error.message);
      this.spinnerService.hide();
    });
  }

  loadAllDagInfo() {
    this.spinnerService.show();
    this.dagInfoService.fetchAllDagInfo().subscribe((data: any) => {
      this.reqObj.dagInfo = data;
      console.log(this.reqObj.dagInfo);
    }, (error) => {
      this.toastr.error(error.error.message);
      this.spinnerService.hide();
    });
  }

  loadDagInfoById(id) {
    this.spinnerService.show();
    this.dagInfoService.fetchAllDagInfoById(id).subscribe((data: any) => {
      this.reqObj.dagInfo = data;
    }, (error) => {
      this.toastr.error(error.error.message);
      this.spinnerService.hide();
    });
  }

  restLand(val) {
    this.dagInfoObj.restLandAfterLa = this.dagInfoObj.recordQuantity - this.dagInfoObj.govtLa;
    console.log("restLandAfterAcq : ", this.dagInfoObj.restLandAfterLa);
  }

  availLand(val) {
    this.dagInfoObj.availableLand = this.dagInfoObj.restLandAfterLa - this.dagInfoObj.totalPreBought;
    console.log("availableLand : ", this.dagInfoObj.availableLand);
  }

  ngOnDestroy(): void {
    this.requisitionSharedService.setObj(null);
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.createRequisitionForm.controls;
  }
  get ownerF() {
    return this.addOwnerForm.controls;
  }
  get getDivision() {
    return this.createRequisitionForm.get("division");
  }
  get getDistrict() {
    return this.createRequisitionForm.get("district");
  }
  get getThana() {
    return this.createRequisitionForm.get("thana");
  }
  get getMouza() {
    return this.createRequisitionForm.get("mouza");
  }
  get getKhatianType() {
    return this.createRequisitionForm.get("khatianType");
  }
  get getLandType() {
    return this.createRequisitionForm.get("landType");
  }
  get getDagInfo() {
    return this.createRequisitionForm.get("dagInfo");
  }

}
