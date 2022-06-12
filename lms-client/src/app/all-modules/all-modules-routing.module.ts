import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserGuard } from '../guard/user.guard';
import { AllModulesComponent } from './all-modules.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },
  {
    path: '',
    component: AllModulesComponent,
    children: [
      {
        path: 'dashboard',
        loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)
      },
      {
        path: 'apps',
        loadChildren: () => import('./apps/apps.module').then(m => m.AppsModule)
      },
      {
        path: 'purchase',
        loadChildren: () => import('./purchase/purchase.module').then(m => m.PurchaseModule),
        canActivate:[UserGuard],
      },
      {
        path: 'clients',
        loadChildren: () => import('./clients/clients.module').then(m => m.ClientsModule)
      },

      {
        path: 'leads',
        loadChildren: () => import('./leads/leads.module').then(m => m.LeadsModule)
      },
      {
        path: 'tickets',
        loadChildren: () => import('./tickets/tickets.module').then(m => m.TicketsModule)
      },
      {
        path: 'performance',
        loadChildren: () => import('./performance/performance.module').then(m => m.PerformanceModule)
      },
      {
        path: 'assets',
        loadChildren: () => import('./assets/assets.module').then(m => m.AssetsModule)
      },

      {
        path: 'activities',
        loadChildren: () => import('./activities/activities.module').then(m => m.ActivitiesModule)
      },
      {
        path: 'users',
        loadChildren: () => import('./users/users.module').then(m => m.UsersModule)
      },
      {
        path: 'settings',
        loadChildren: () => import('./settings/settings.module').then(m => m.SettingsModule)
      },
      {
        path: 'pages',
        loadChildren: () => import('./pages/pages.module').then(m => m.PagesModule)
      },
       {
        path: 'components',
        loadChildren: () => import('./components/components.module').then(m => m.ComponentsModule)
      },
      {
        path: 'forms',
        loadChildren: () => import('./forms/forms.module').then(m => m.FormsModule)
      },
      {
        path: 'tables',
        loadChildren: () => import('./tables/tables.module').then(m => m.TablesModule)
      },

      {
        path: 'category',
        loadChildren: () => import('./category/category.module').then(m => m.CategoryModule)
      },
     
      {
        path: 'taskreports',
        loadChildren: () => import('./taskreports/taskreports.module').then(m => m.TaskreportsModule)
      },
      {
        path: 'userreports',
        loadChildren: () => import('./userreports/userreports.module').then(m => m.UserreportsModule)
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AllModulesRoutingModule { }
