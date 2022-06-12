import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RequisitionService {


  public baseUrl = environment.baseUrl;
  private END_POINT = `purchase/requisition`;

  private CREATE = `${this.baseUrl}/${this.END_POINT}/create`;
  private UPDATE = `${this.baseUrl}/${this.END_POINT}/update`;
  private DELETE = `${this.baseUrl}/${this.END_POINT}/delete`;
  private DELETE_BY_ID = `${this.baseUrl}/${this.END_POINT}/delete-by-id`;
  private FIND_BY_ID = `${this.baseUrl}/${this.END_POINT}/find-by-id`;
  private GETALL = `${this.baseUrl}/${this.END_POINT}/get-all`;

  constructor(
    private http: HttpClient
  ) { }

  getAllRequisition(queryParams) {
    return this.http.get(this.GETALL,{params: queryParams}).pipe(retry(3));
  }

  public createRequisition(reqObj) {
    return this.http.post(this.CREATE, reqObj);
  }

  // Get requisition By id
  public getRequisitionById(id){
    return this.http.get(this.FIND_BY_ID, {
      params: new HttpParams().set('id', id)
    }).pipe(
      map((data: any) => data
      ))
  }

  public sendGetRequest(apiURL, queryParams) {
    console.log("@sendGetRequest");
    console.log(apiURL);
    return this.http.get(apiURL, { params: queryParams }).pipe(retry(3));
  }

  public sendPutRequest(apiURL, formData){
    console.log("@sendPutRequest");
    return this.http.put(apiURL, formData);
  }

  public sendDeleteRequest(id){
    console.log("@sendDeleteRequest");
    return this.http.delete(this.DELETE_BY_ID, {
      params: new HttpParams().set('id', id)
    }).pipe(
      map((data: any) => data
      ))
  }
}
