import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { AdsService } from '../../services/ads';
import { CategoriesService } from '../../services/categories';
import { AdImagesService } from '../../services/ad-images';

import { AdRequest, AdType } from '../../models/ad-request';

import { Category } from '../../models/category';

import { Header } from '../../../../shared/components/layout/header/header';

@Component({
  selector: 'app-ad-create',
  imports: [CommonModule, ReactiveFormsModule, RouterLink, Header],
  templateUrl: './ad-create.html',
  styleUrl: './ad-create.scss',
})
export class AdCreate {
  private readonly fb = inject(FormBuilder);

  private readonly adsService = inject(AdsService);

  private readonly categoriesService = inject(CategoriesService);

  private readonly adImagesService = inject(AdImagesService);

  private readonly router = inject(Router);

  readonly categories = signal<Category[]>([]);

  readonly isLoadingCategories = signal(true);

  readonly categoriesError = signal(false);

  readonly isSubmitting = signal(false);

  readonly hasError = signal(false);

  readonly errorMessage = signal('');

  readonly form = this.fb.nonNullable.group({
    titulo: ['', [Validators.required, Validators.maxLength(100)]],

    descricao: ['', [Validators.required]],

    tipo: ['VENDA' as AdType, [Validators.required]],

    preco: [null as number | null],

    categoryId: [null as number | null, [Validators.required]],

    imageUrl: ['', [Validators.required, Validators.maxLength(500)]],
  });

  constructor() {
    this.configureTypeChanges();
    this.loadCategories();
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
    this.isLoadingCategories.set(true);

    this.categoriesError.set(false);

    this.categoriesService.getCategories().subscribe({
      next: (categories) => {
        this.categories.set(categories);

        this.isLoadingCategories.set(false);
      },

      error: (error) => {
        console.error('Erro ao carregar categorias:', error);

        this.categoriesError.set(true);

        this.isLoadingCategories.set(false);
      },
    });
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
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

    this.errorMessage.set('');

    this.adsService.createAd(request).subscribe({
      next: (createdAd) => {
        const imageUrl = value.imageUrl.trim();

        this.adImagesService
          .create({
            anuncioId: createdAd.id,

            url: imageUrl,

            ordem: 0,
          })
          .subscribe({
            next: () => {
              this.isSubmitting.set(false);

              this.router.navigate(['/ads', createdAd.id]);
            },

            error: (error) => {
              console.error('Erro ao cadastrar imagem:', error);

              this.isSubmitting.set(false);

              this.hasError.set(true);

              this.errorMessage.set(
                'O anúncio foi criado, mas não foi possível cadastrar a imagem.',
              );
            },
          });
      },

      error: (error) => {
        console.error('Erro ao criar anúncio:', error);

        this.isSubmitting.set(false);

        if (error.status === 401 || error.status === 403) {
          this.router.navigate(['/login'], {
            queryParams: {
              returnUrl: '/ads/create',
            },
          });

          return;
        }

        this.hasError.set(true);

        this.errorMessage.set(
          error.error?.message ??
            'Não foi possível criar o anúncio. Verifique os dados e tente novamente.',
        );
      },
    });
  }
}
