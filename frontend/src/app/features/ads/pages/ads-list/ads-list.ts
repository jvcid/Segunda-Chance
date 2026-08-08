import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Ad } from '../../models/ad';
import { AdsService } from '../../services/ads';
import { Header } from '../../../../shared/components/layout/header/header';

@Component({
  selector: 'app-ads-list',
  imports: [Header],
  templateUrl: './ads-list.html',
  styleUrl: './ads-list.scss',
})
export class AdsList implements OnInit {
  private readonly adsService = inject(AdsService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  readonly ads = signal<Ad[]>([]);
  readonly isLoading = signal(true);
  readonly hasError = signal(false);

  readonly currentPage = signal(0);
  readonly totalPages = signal(0);

  readonly searchTerm = signal('');
  readonly selectedCategory = signal('');
  readonly selectedType = signal('');

  readonly categories = [
    'Livros',
    'Computação',
    'Engenharia',
    'Eletrônicos',
    'Materiais acadêmicos',
    'Outros',
  ];

  ngOnInit(): void {
    const search = this.route.snapshot.queryParamMap.get('search');
    const category = this.route.snapshot.queryParamMap.get('category');

    if (search) {
      this.searchTerm.set(search);
    }

    if (category) {
      this.selectedCategory.set(category);
    }

    this.loadAds();
  }

  loadAds(page = 0): void {
    this.isLoading.set(true);
    this.hasError.set(false);

    const search = this.searchTerm().trim();

    this.adsService
      .getAds(
        page,
        8,
        search || undefined,
        undefined,
        this.selectedType() ? (this.selectedType() as 'VENDA' | 'DOACAO') : undefined,
      )
      .subscribe({
        next: (response) => {
          this.ads.set(response.content);
          this.currentPage.set(response.number);
          this.totalPages.set(response.totalPages);

          this.isLoading.set(false);
        },

        error: () => {
          this.hasError.set(true);
          this.isLoading.set(false);
        },
      });
  }

  search(value: string): void {
    this.searchTerm.set(value);
    this.currentPage.set(0);
    this.loadAds(0);
  }

  filterByCategory(category: string): void {
    this.selectedCategory.set(category);
    this.currentPage.set(0);

    /*
     * Por enquanto a Landing e a interface trabalham com o nome
     * da categoria. Depois ligaremos isso aos IDs reais vindos
     * de /api/categories.
     */
    this.loadAds(0);
  }

  filterByType(type: string): void {
    this.selectedType.set(type);
    this.currentPage.set(0);
    this.loadAds(0);
  }

  clearFilters(): void {
    this.searchTerm.set('');
    this.selectedCategory.set('');
    this.selectedType.set('');

    this.loadAds(0);
  }

  openAd(id: number): void {
    this.router.navigate(['/ads', id]);
  }

  previousPage(): void {
    if (this.currentPage() > 0) {
      this.loadAds(this.currentPage() - 1);
    }
  }

  nextPage(): void {
    if (this.currentPage() + 1 < this.totalPages()) {
      this.loadAds(this.currentPage() + 1);
    }
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
