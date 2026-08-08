import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Ad } from '../../models/ad';
import { Category } from '../../models/category';

import { AdsService } from '../../services/ads';
import { CategoriesService } from '../../services/categories';

import { Header } from '../../../../shared/components/layout/header/header';

@Component({
  selector: 'app-ads-list',
  imports: [Header],
  templateUrl: './ads-list.html',
  styleUrl: './ads-list.scss',
})
export class AdsList implements OnInit {
  private readonly adsService = inject(AdsService);
  private readonly categoriesService = inject(CategoriesService);

  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  readonly ads = signal<Ad[]>([]);
  readonly categories = signal<Category[]>([]);

  readonly isLoading = signal(true);
  readonly hasError = signal(false);

  readonly isLoadingCategories = signal(true);
  readonly categoriesError = signal(false);

  readonly currentPage = signal(0);
  readonly totalPages = signal(0);

  readonly searchTerm = signal('');

  readonly selectedCategoryId = signal<number | null>(null);

  readonly selectedType = signal<'' | 'VENDA' | 'DOACAO'>('');

  ngOnInit(): void {
    const search = this.route.snapshot.queryParamMap.get('search');

    if (search) {
      this.searchTerm.set(search);
    }

    this.loadCategories();
  }

  loadCategories(): void {
    this.isLoadingCategories.set(true);
    this.categoriesError.set(false);

    this.categoriesService.getCategories().subscribe({
      next: (categories) => {
        this.categories.set(categories);

        this.isLoadingCategories.set(false);

        /*
         * A Landing ainda envia:
         *
         * /ads?category=Livros
         *
         * Aqui convertemos esse nome para o ID real
         * retornado pelo backend.
         */
        const categoryName = this.route.snapshot.queryParamMap.get('category');

        if (categoryName) {
          const category = categories.find(
            (item) => item.name.toLowerCase() === categoryName.toLowerCase(),
          );

          if (category) {
            this.selectedCategoryId.set(category.id);
          }
        }

        this.loadAds(0);
      },

      error: (error) => {
        console.error('Erro ao carregar categorias:', error);

        this.categoriesError.set(true);
        this.isLoadingCategories.set(false);

        /*
         * Mesmo se as categorias falharem,
         * os anúncios ainda podem ser carregados.
         */
        this.loadAds(0);
      },
    });
  }

  loadAds(page = 0): void {
    this.isLoading.set(true);
    this.hasError.set(false);

    const search = this.searchTerm().trim();

    const categoryId = this.selectedCategoryId() ?? undefined;

    const tipo = this.selectedType() || undefined;

    this.adsService.getAds(page, 8, search || undefined, categoryId, tipo, 'DISPONIVEL').subscribe({
      next: (response) => {
        this.ads.set(response.content);

        this.currentPage.set(response.number);

        this.totalPages.set(response.totalPages);

        this.isLoading.set(false);
      },

      error: (error) => {
        console.error('Erro ao carregar anúncios:', error);

        this.hasError.set(true);
        this.isLoading.set(false);
      },
    });
  }

  search(value: string): void {
    this.searchTerm.set(value.trim());

    this.currentPage.set(0);

    this.loadAds(0);
  }

  filterByCategory(categoryId: number | null): void {
    this.selectedCategoryId.set(categoryId);

    this.currentPage.set(0);

    this.loadAds(0);
  }

  filterByType(type: '' | 'VENDA' | 'DOACAO'): void {
    this.selectedType.set(type);

    this.currentPage.set(0);

    this.loadAds(0);
  }

  clearFilters(): void {
    this.searchTerm.set('');

    this.selectedCategoryId.set(null);

    this.selectedType.set('');

    this.currentPage.set(0);

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
