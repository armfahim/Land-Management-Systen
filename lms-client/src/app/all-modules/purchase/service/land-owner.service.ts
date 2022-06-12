import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LandOwnerService {
  public baseUrl = environment.baseUrl;
  private END_POINT = `api/land-owner`;

  private CREATE = `${this.baseUrl}/${this.END_POINT}/create`;
  private UPDATE = `${this.baseUrl}/${this.END_POINT}/update`;
  private DELETE = `${this.baseUrl}/${this.END_POINT}/delete`;
  private FIND_BY_ID = `${this.baseUrl}/${this.END_POINT}/find-by-id`;
  private GET_ALL = `${this.baseUrl}/${this.END_POINT}/get-all`;

  constructor(
    private http: HttpClient
  ) { }

  // Get all List
  getAllList(): Observable<any> {
    return this.http.get<any>(this.GET_ALL);
  }

  // Get all List 2
  getAllList2() {
    return this.http.get(this.GET_ALL).pipe(
      map((data: any) => data)
    );
  }

  // Get all List by id or something else
  // getAllListById(params: HttpParams): Observable<any> {
  //   return this.http.get<any>(this.LIST_BY_ID, { params });
  // }

  findById(id: any) {
    return this.http.get(this.FIND_BY_ID, {
      params: new HttpParams().set('id', id)
    }).pipe(
      map((data: any) => data
      ))
  }


  onSaveItem(reqObj: any): Observable<any> {
    return this.http.post<any>(this.CREATE, reqObj).pipe(
      map((data: any) => data
      ));
  }

  onUpdateItem(reqObj: any): Observable<any> {
    return this.http.put<any>(this.UPDATE, reqObj).pipe(
      map((data: any) => data
      ));
  }

  onDelete(reqObj: any): Observable<any> {
    const params = new HttpParams().set('id', reqObj);
    return this.http.delete<any>(this.DELETE, { params }).pipe(
      map((data: any) => data
      ));
  }
}
