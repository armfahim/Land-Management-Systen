import { Mouza } from "./mouza";

export class DagInfo{
    id!:number;
	dagNumber!:string;
	recordQuantity!:number;
	govtLa!:number;
	restLandAfterLa!:number;
	totalPreBought!:number;
	availableLand!:number;
	isActive!:boolean;
	mouza!:Mouza
}