import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

import { Ad } from '../../models/ad';
import { AdsService } from '../../services/ads';

import { AuthService } from '../../../auth/services/auth';
import { RequestsService } from '../../../requests/services/requests';

import { Header } from '../../../../shared/components/layout/header/header';

@Component({
  selector: 'app-ad-details',
  imports: [Header, RouterLink, ReactiveFormsModule],
  templateUrl: './ad-details.html',
  styleUrl: './ad-details.scss',
})
export class AdDetails implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  private readonly adsService = inject(AdsService);

  private readonly authService = inject(AuthService);

  private readonly requestsService = inject(RequestsService);

  private readonly fb = inject(FormBuilder);

  readonly ad = signal<Ad | null>(null);

  readonly isLoading = signal(true);
  readonly hasError = signal(false);

  readonly isLoggedIn = this.authService.isLoggedIn;

  readonly showRequestForm = signal(false);

  readonly isSendingRequest = signal(false);

  readonly requestSuccess = signal(false);

  readonly requestError = signal('');

  readonly requestForm = this.fb.nonNullable.group({
    mensagem: ['', [Validators.required, Validators.maxLength(500)]],
  });

  ngOnInit(): void {
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

    this.loadAd(id);
  }

  loadAd(id: number): void {
    this.isLoading.set(true);
    this.hasError.set(false);

    this.adsService.getAdById(id).subscribe({
      next: (response) => {
        this.ad.set(response);

        this.isLoading.set(false);
      },

      error: (error) => {
        this.isLoading.set(false);

        if (error.status === 404) {
          this.router.navigate(['/404']);
          return;
        }

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

  requestItem(): void {
    const currentAd = this.ad();

    if (!currentAd) {
      return;
    }

    if (!this.isLoggedIn()) {
      this.router.navigate(['/login'], {
        queryParams: {
          returnUrl: `/ads/${currentAd.id}`,
        },
      });

      return;
    }

    this.requestSuccess.set(false);
    this.requestError.set('');

    this.showRequestForm.set(true);
  }

  cancelRequest(): void {
    this.showRequestForm.set(false);

    this.requestForm.reset({
      mensagem: '',
    });

    this.requestError.set('');
  }

  sendRequest(): void {
    const currentAd = this.ad();

    if (!currentAd) {
      return;
    }

    if (!this.isLoggedIn()) {
      this.router.navigate(['/login'], {
        queryParams: {
          returnUrl: `/ads/${currentAd.id}`,
        },
      });

      return;
    }

    if (this.requestForm.invalid) {
      this.requestForm.markAllAsTouched();
      return;
    }

    const mensagem = this.requestForm.controls.mensagem.value.trim();

    if (!mensagem) {
      return;
    }

    this.isSendingRequest.set(true);
    this.requestError.set('');

    this.requestsService
      .create({
        anuncioId: currentAd.id,
        mensagem,
      })
      .subscribe({
        next: () => {
          this.isSendingRequest.set(false);

          this.showRequestForm.set(false);

          this.requestSuccess.set(true);

          this.requestForm.reset({
            mensagem: '',
          });
        },

        error: (error) => {
          console.error('Erro ao criar solicitação:', error);

          this.isSendingRequest.set(false);

          if (error.status === 401 || error.status === 403) {
            this.router.navigate(['/login'], {
              queryParams: {
                returnUrl: `/ads/${currentAd.id}`,
              },
            });

            return;
          }

          this.requestError.set(error.error?.message ?? 'Não foi possível enviar sua solicitação.');
        },
      });
  }
}
