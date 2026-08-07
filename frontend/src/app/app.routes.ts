import { Routes } from '@angular/router';

export const routes: Routes = [
  // Landing
  {
    path: '',
    loadComponent: () => import('./features/landing/pages/landing/landing').then((m) => m.Landing),
    title: 'Segunda Chance',
  },

  // Autenticação
  {
    path: 'login',
    loadComponent: () => import('./features/auth/pages/login/login').then((m) => m.Login),
    title: 'Login | Segunda Chance',
  },
  {
    path: 'register',
    loadComponent: () => import('./features/auth/pages/register/register').then((m) => m.Register),
    title: 'Cadastro | Segunda Chance',
  },

  // Anúncios
  {
    path: 'ads',
    loadComponent: () => import('./features/ads/pages/ads-list/ads-list').then((m) => m.AdsList),
    title: 'Anúncios | Segunda Chance',
  },
  {
    path: 'ads/create',
    loadComponent: () => import('./features/ads/pages/ad-create/ad-create').then((m) => m.AdCreate),
    title: 'Criar anúncio | Segunda Chance',
  },
  {
    path: 'ads/edit/:id',
    loadComponent: () => import('./features/ads/pages/ad-edit/ad-edit').then((m) => m.AdEdit),
    title: 'Editar anúncio | Segunda Chance',
  },
  {
    path: 'ads/:id',
    loadComponent: () =>
      import('./features/ads/pages/ad-details/ad-details').then((m) => m.AdDetails),
    title: 'Detalhes do anúncio | Segunda Chance',
  },
  {
    path: 'my-ads',
    loadComponent: () => import('./features/ads/pages/my-ads/my-ads').then((m) => m.MyAds),
    title: 'Meus anúncios | Segunda Chance',
  },

  // Perfil
  {
    path: 'profile',
    loadComponent: () => import('./features/profile/pages/profile/profile').then((m) => m.Profile),
    title: 'Meu perfil | Segunda Chance',
  },
  {
    path: 'profile/edit',
    loadComponent: () =>
      import('./features/profile/pages/profile-edit/profile-edit').then((m) => m.ProfileEdit),
    title: 'Editar perfil | Segunda Chance',
  },

  // Solicitações
  {
    path: 'requests',
    loadComponent: () =>
      import('./features/requests/pages/requests-dashboard/requests-dashboard').then(
        (m) => m.RequestsDashboard,
      ),
    title: 'Solicitações | Segunda Chance',
  },
  {
    path: 'requests/sent',
    loadComponent: () =>
      import('./features/requests/pages/requests-sent/requests-sent').then((m) => m.RequestsSent),
    title: 'Solicitações enviadas | Segunda Chance',
  },
  {
    path: 'requests/received',
    loadComponent: () =>
      import('./features/requests/pages/requests-received/requests-received').then(
        (m) => m.RequestsReceived,
      ),
    title: 'Solicitações recebidas | Segunda Chance',
  },

  // Sobre
  {
    path: 'about',
    loadComponent: () => import('./features/about/pages/about/about').then((m) => m.About),
    title: 'Sobre | Segunda Chance',
  },

  // 404
  {
    path: '404',
    loadComponent: () => import('./pages/not-found/not-found').then((m) => m.NotFound),
    title: 'Página não encontrada | Segunda Chance',
  },

  // Qualquer endereço inexistente
  {
    path: '**',
    redirectTo: '404',
  },
];
