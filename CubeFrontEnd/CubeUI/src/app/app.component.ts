import { Component, OnInit } from '@angular/core';

import { ProductCatalogService } from './service/product-catalog.service';
import { Product } from './model/product';

import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CustomerInfo } from './model/customer.info';
import { CustomerStore } from './stores/customer.store';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
title='CUBE UI'
  isCollapsed = true;
  customer$: Observable<CustomerInfo>;

  constructor(
    private router: Router,
    private customerStore: CustomerStore) {
      this.customerStore.init();
    }

  ngOnInit() {
    this.customer$ = this.customerStore.getAll$();
  }

  doLogin(): void {
    this.customerStore.login();
  }


  async doLogout() {
    await this.router.navigate(['/']);
    await this.customerStore.logout();
  }

}
