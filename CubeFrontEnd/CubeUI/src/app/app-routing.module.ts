import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppAuthGuard } from './app-auth.guard';
import { MenuComponent } from './menu/menu.component';

const routes: Routes = [
  { path: 'menu', component: MenuComponent, canActivate: [AppAuthGuard]  },

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
