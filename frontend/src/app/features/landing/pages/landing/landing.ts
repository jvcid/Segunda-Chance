import { Component, inject, OnInit, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

import { AdsService } from '../../../ads/services/ads';
import { Ad } from '../../../ads/models/ad';
import { AuthService } from '../../../auth/services/auth';
import { Header } from '../../../../shared/components/layout/header/header';

@Component({
  selector: 'app-landing',
  imports: [Header, RouterLink],
  templateUrl: './landing.html',
  styleUrl: './landing.scss',
})
export class Landing implements OnInit {
  private readonly router = inject(Router);
  private readonly authService = inject(AuthService);
  private readonly adsService = inject(AdsService);

  readonly isLoggedIn = this.authService.isLoggedIn;

  readonly searchTerm = signal('');

  readonly recentAds = signal<Ad[]>([]);
  readonly isLoadingAds = signal(true);
  readonly adsError = signal(false);

  readonly categories = [
    'Livros',
    'Computação',
    'Engenharia',
    'Eletrônicos',
    'Materiais acadêmicos',
    'Outros',
  ];

  readonly statistics = [
    {
      value: '120+',
      label: 'itens anunciados',
    },
    {
      value: '75+',
      label: 'itens reutilizados',
    },
    {
      value: '90+',
      label: 'membros participantes',
    },
  ];

  ngOnInit(): void {
    this.loadRecentAds();
  }

  loadRecentAds(): void {
    this.isLoadingAds.set(true);
    this.adsError.set(false);

    this.adsService.getAds(0, 4).subscribe({
      next: (response) => {
        this.recentAds.set(response.content);
        this.isLoadingAds.set(false);
      },

      error: () => {
        this.adsError.set(true);
        this.isLoadingAds.set(false);
      },
    });
  }

  search(value: string): void {
    const term = value.trim();

    if (!term) {
      this.router.navigate(['/ads']);
      return;
    }

    this.router.navigate(['/ads'], {
      queryParams: {
        search: term,
      },
    });
  }

  openCategory(category: string): void {
    this.router.navigate(['/ads'], {
      queryParams: {
        category,
      },
    });
  }

  openAd(id: number): void {
    this.router.navigate(['/ads', id]);
  }

  createAd(): void {
    this.router.navigate(['/ads/create']);
  }

  formatPrice(ad: Ad): string {
    if (ad.tipo === 'DOACAO') {
      return 'Doação';
    }

    if (ad.preco === null) {
      return 'Preço não informado';
    }

    return ad.preco.toLocaleString('pt-BR', {
      style: 'currency',
      currency: 'BRL',
    });
  }
}
