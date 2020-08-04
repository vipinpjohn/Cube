import { Component, OnInit } from '@angular/core';
import { ProductCatalogService } from '../service/product-catalog.service';
import { Product } from '../model/product';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  selectedProductCatalog: Product;
  productCatalogs: Product[] = [];

  constructor(private productCatalogService: ProductCatalogService) { }

  ngOnInit() {
   this.productCatalogService.getProductCatalogs()
     .subscribe(data => {
       this.productCatalogs = data;
     });
  }

  changeProductCatalog(productCatalog: Product) {
   this.selectedProductCatalog = productCatalog;
  }

}
