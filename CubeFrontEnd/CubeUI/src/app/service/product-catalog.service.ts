import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Product } from './../model/product';

const headers = new HttpHeaders().set('Content-Type', 'application/json');
const apiUrl = "http://localhost:7030/main/product";

@Injectable({
  providedIn: 'root'
})
export class ProductCatalogService {

  constructor(private http: HttpClient) { }

  getProductCatalogs (): Observable<Product[]> {
      return this.http.get<Product[]>(apiUrl, {headers});
  }

  getProductCatalog(id: string): Observable<Product> {
    return this.http.get<Product>(`${apiUrl}/${id}`, {headers});
  }





}
