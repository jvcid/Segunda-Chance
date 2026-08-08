import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

import { AdsService } from '../../services/ads';
import { CategoriesService } from '../../services/categories';

import { AdRequest, AdType } from '../../models/ad-request';

import { Category } from '../../models/category';

import { Header } from '../../../../shared/components/layout/header/header';

@Component({
  selector: 'app-ad-edit',
  imports: [CommonModule, ReactiveFormsModule, RouterLink, Header],
  templateUrl: './ad-edit.html',
  styleUrl: './ad-edit.scss',
})
export class AdEdit {
  private readonly fb = inject(FormBuilder);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  private readonly adsService = inject(AdsService);
  private readonly categoriesService = inject(CategoriesService);

  readonly categories = signal<Category[]>([]);

  readonly isLoading = signal(true);
  readonly isSubmitting = signal(false);

  readonly hasError = signal(false);
  readonly errorMessage = signal('');

  readonly adId = signal<number | null>(null);

  readonly form = this.fb.nonNullable.group({
    titulo: ['', [Validators.required, Validators.maxLength(100)]],

    descricao: ['', [Validators.required]],

    tipo: ['VENDA' as AdType, [Validators.required]],

    preco: [null as number | null],

    categoryId: [null as number | null, [Validators.required]],
  });

  constructor() {
    this.configureTypeChanges();
    this.loadCategories();
    this.loadAd();
  }

  private configureTypeChanges(): void {
    this.updatePriceValidators(this.form.controls.tipo.value);

    this.form.controls.tipo.valueChanges.subscribe((tipo) => {
      if (tipo === 'DOACAO') {
        this.form.controls.preco.setValue(null);
      }

      this.updatePriceValidators(tipo);
    });
  }

  private updatePriceValidators(tipo: AdType): void {
    const priceControl = this.form.controls.preco;

    if (tipo === 'VENDA') {
      priceControl.setValidators([Validators.required, Validators.min(0)]);
    } else {
      priceControl.clearValidators();
    }

    priceControl.updateValueAndValidity();
  }

  loadCategories(): void {
    this.categoriesService.getCategories().subscribe({
      next: (categories) => {
        this.categories.set(categories);
      },

      error: (error) => {
        console.error('Erro ao carregar categorias:', error);
      },
    });
  }

  loadAd(): void {
    const idParam = this.route.snapshot.paramMap.get('id');

    if (!idParam) {
      this.router.navigate(['/404']);
      return;
    }

    const id = Number(idParam);

    if (Number.isNaN(id)) {
      this.router.navigate(['/404']);
      return;
    }

    this.adId.set(id);

    this.adsService.getAdById(id).subscribe({
      next: (ad) => {
        this.form.patchValue({
          titulo: ad.titulo,
          descricao: ad.descricao,
          tipo: ad.tipo,
          preco: ad.preco,
          categoryId: ad.categoryId,
        });

        this.updatePriceValidators(ad.tipo);

        this.isLoading.set(false);
      },

      error: (error) => {
        console.error('Erro ao carregar anúncio:', error);

        if (error.status === 404) {
          this.router.navigate(['/404']);
          return;
        }

        this.hasError.set(true);

        this.errorMessage.set('Não foi possível carregar o anúncio.');

        this.isLoading.set(false);
      },
    });
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const id = this.adId();

    if (id === null) {
      return;
    }

    const value = this.form.getRawValue();

    if (value.categoryId === null) {
      return;
    }

    const request: AdRequest = {
      titulo: value.titulo.trim(),

      descricao: value.descricao.trim(),

      tipo: value.tipo,

      preco: value.tipo === 'DOACAO' ? null : value.preco,

      categoryId: value.categoryId,
    };

    this.isSubmitting.set(true);

    this.hasError.set(false);

    this.adsService.updateAd(id, request).subscribe({
      next: (updatedAd) => {
        this.isSubmitting.set(false);

        this.router.navigate(['/ads', updatedAd.id]);
      },

      error: (error) => {
        console.error('Erro ao atualizar anúncio:', error);

        this.isSubmitting.set(false);
        this.hasError.set(true);

        this.errorMessage.set(error.error?.message ?? 'Não foi possível atualizar o anúncio.');
      },
    });
  }
}
