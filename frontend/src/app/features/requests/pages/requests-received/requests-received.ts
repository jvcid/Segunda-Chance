import { Component, inject, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

import { Request } from '../../models/request';
import { RequestsService } from '../../services/requests';

import { Header } from '../../../../shared/components/layout/header/header';

@Component({
  selector: 'app-requests-received',
  imports: [Header, RouterLink],
  templateUrl: './requests-received.html',
  styleUrl: './requests-received.scss',
})
export class RequestsReceived {
  private readonly requestsService = inject(RequestsService);
  private readonly router = inject(Router);

  readonly requests = signal<Request[]>([]);

  readonly isLoading = signal(true);
  readonly hasError = signal(false);

  readonly processingId = signal<number | null>(null);

  constructor() {
    this.loadRequests();
  }

  loadRequests(): void {
    this.isLoading.set(true);
    this.hasError.set(false);

    this.requestsService.getReceived().subscribe({
      next: (requests) => {
        this.requests.set(requests);
        this.isLoading.set(false);
      },

      error: (error) => {
        console.error('Erro ao carregar solicitações recebidas:', error);

        this.hasError.set(true);
        this.isLoading.set(false);
      },
    });
  }

  openAd(anuncioId: number): void {
    this.router.navigate(['/ads', anuncioId]);
  }

  approve(event: Event, id: number): void {
    event.stopPropagation();

    this.processingId.set(id);

    this.requestsService.approve(id).subscribe({
      next: (updatedRequest) => {
        this.updateRequest(updatedRequest);

        this.processingId.set(null);
      },

      error: (error) => {
        console.error('Erro ao aprovar solicitação:', error);

        this.processingId.set(null);
      },
    });
  }

  reject(event: Event, id: number): void {
    event.stopPropagation();

    this.processingId.set(id);

    this.requestsService.reject(id).subscribe({
      next: (updatedRequest) => {
        this.updateRequest(updatedRequest);

        this.processingId.set(null);
      },

      error: (error) => {
        console.error('Erro ao rejeitar solicitação:', error);

        this.processingId.set(null);
      },
    });
  }

  private updateRequest(updatedRequest: Request): void {
    this.requests.update((requests) =>
      requests.map((request) => (request.id === updatedRequest.id ? updatedRequest : request)),
    );
  }

  statusLabel(status: string): string {
    switch (status) {
      case 'PENDENTE':
        return 'Pendente';

      case 'APROVADA':
        return 'Aprovada';

      case 'REJEITADA':
        return 'Rejeitada';

      default:
        return status;
    }
  }
}
