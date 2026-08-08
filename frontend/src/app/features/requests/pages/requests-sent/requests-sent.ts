import { Component, inject, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

import { Request } from '../../models/request';
import { RequestsService } from '../../services/requests';

import { Header } from '../../../../shared/components/layout/header/header';

@Component({
  selector: 'app-requests-sent',
  imports: [Header, RouterLink],
  templateUrl: './requests-sent.html',
  styleUrl: './requests-sent.scss',
})
export class RequestsSent {
  private readonly requestsService = inject(RequestsService);

  private readonly router = inject(Router);

  readonly requests = signal<Request[]>([]);

  readonly isLoading = signal(true);

  readonly hasError = signal(false);

  readonly deletingId = signal<number | null>(null);

  constructor() {
    this.loadRequests();
  }

  loadRequests(): void {
    this.isLoading.set(true);
    this.hasError.set(false);

    this.requestsService.getSent().subscribe({
      next: (requests) => {
        this.requests.set(requests);

        this.isLoading.set(false);
      },

      error: (error) => {
        console.error('Erro ao carregar solicitações enviadas:', error);

        this.hasError.set(true);
        this.isLoading.set(false);
      },
    });
  }

  openAd(anuncioId: number): void {
    this.router.navigate(['/ads', anuncioId]);
  }

  deleteRequest(event: Event, id: number): void {
    event.stopPropagation();

    this.deletingId.set(id);

    this.requestsService.delete(id).subscribe({
      next: () => {
        this.requests.update((requests) => requests.filter((request) => request.id !== id));

        this.deletingId.set(null);
      },

      error: (error) => {
        console.error('Erro ao excluir solicitação:', error);

        this.deletingId.set(null);
      },
    });
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
