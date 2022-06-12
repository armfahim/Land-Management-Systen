import { DagInfo } from "./dag-info";
import { Districts } from "./districts";
import { Divisions } from "./divisions";
import { KhatianType } from "./Khatian-type";
import { LandType } from "./land-type";
import { Mouza } from "./mouza";
import { Thanas } from "./thana";

export class Requisition {
	id!:number;
    name!:string;

	// Requisition Info
	requisitionNo!:number;
	reqDate!:Date;

	// Broker Info
	saleMedia!:string;
	brokerName!:string;
	contactNumber!:string;
	brokerNid!:string;

	// Land Info
	offeringLand!:number;
	division:Divisions;
	district!:Districts;
	thana!:Thanas;
	mouza!:Mouza;
	khatianType!:KhatianType;
	khatianNo!:number;
	dagInfo!:DagInfo;
	landType!:LandType;
	sellingQty!:number;
	govtMouzaRate!:number;
	offeringPrice!:number;
	sourceRemarks!:string;

}
