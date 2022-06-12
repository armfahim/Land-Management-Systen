import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RequisitionSharedService {
  private idSource: BehaviorSubject<string> = new BehaviorSubject<string>(null);
  private objSource: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  id$ =  this.idSource.asObservable();
  objSource$ =  this.objSource.asObservable();

  constructor() { }

  setId(id: any) {
    this.idSource.next(id);
  }

  setObj(obj: any) {
    this.objSource.next(obj);
  }
}
