import { Component, inject, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

import { AdsService } from '../../services/ads';
import { Ad } from '../../models/ad';

import { Header } from '../../../../shared/components/layout/header/header';

@Component({
  selector: 'app-my-ads',
  imports: [Header, RouterLink],
  templateUrl: './my-ads.html',
  styleUrl: './my-ads.scss',
})
export class MyAds {
  private readonly adsService = inject(AdsService);
  private readonly router = inject(Router);

  readonly ads = signal<Ad[]>([]);

  readonly isLoading = signal(true);
  readonly hasError = signal(false);

  readonly deletingId = signal<number | null>(null);

  readonly confirmDeleteId = signal<number | null>(null);

  constructor() {
    this.loadAds();
  }

  loadAds(): void {
    this.isLoading.set(true);
    this.hasError.set(false);

    this.adsService.getMyAds().subscribe({
      next: (ads) => {
        this.ads.set(ads);
        this.isLoading.set(false);
      },

      error: (error) => {
        console.error('Erro ao carregar meus anúncios:', error);

        this.hasError.set(true);
        this.isLoading.set(false);
      },
    });
  }

  openAd(id: number): void {
    this.router.navigate(['/ads', id]);
  }

  editAd(event: Event, id: number): void {
    event.stopPropagation();

    this.router.navigate(['/ads/edit', id]);
  }

  askDelete(event: Event, id: number): void {
    event.stopPropagation();

    this.confirmDeleteId.set(id);
  }

  cancelDelete(): void {
    this.confirmDeleteId.set(null);
  }

  deleteAd(id: number): void {
    this.deletingId.set(id);

    this.adsService.deleteAd(id).subscribe({
      next: () => {
        this.ads.update((currentAds) => currentAds.filter((ad) => ad.id !== id));

        this.deletingId.set(null);
        this.confirmDeleteId.set(null);
      },

      error: (error) => {
        console.error('Erro ao excluir anúncio:', error);

        this.deletingId.set(null);
        this.confirmDeleteId.set(null);

        this.hasError.set(true);
      },
    });
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
