import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Page} from "../model/page";
import {Product} from "../model/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  public findAll(page:number) {
    return this.http.get<Page>(`api/v1/product/search?page=${page}`);
  }

  public findById(id: number) {
    return this.http.get<Product>(`api/v1/product/${id}`);
  }

  public save(product: Product) {
    if (product.id) {
      return this.http.put('api/v1/product', product);
    } else {
      return this.http.post('api/v1/product', product);
    }
  }
  public delete(id: number) {
    return this.http.delete('api/v1/product/' + id);
  }
}
