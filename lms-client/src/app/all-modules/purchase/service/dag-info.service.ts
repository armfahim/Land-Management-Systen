import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DagInfoService {

  public baseUrl = environment.baseUrl;
  private END_POINT = `dag-info`;

  private CREATE = `${this.baseUrl}/${this.END_POINT}/create`;
  private UPDATE = `${this.baseUrl}/${this.END_POINT}/update`;
  private DELETE = `${this.baseUrl}/${this.END_POINT}/delete`;
  private FIND_BY_ID = `${this.baseUrl}/${this.END_POINT}/find-by-id`;
  private GET_ALL = `${this.baseUrl}/${this.END_POINT}/get-all`;

  constructor(
    private http: HttpClient
  ) { }

  public fetchAllDagInfo() {
    return this.http.get(this.GET_ALL);
  }

  public fetchAllDagInfoById(id){
    console.log(id);
    return this.http.get(`${this.FIND_BY_ID}/${id}`);
  }
}
