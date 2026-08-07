import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

import { Header } from '../../../../shared/components/layout/header/header';

@Component({
  selector: 'app-landing',
  imports: [Header, RouterLink],
  templateUrl: './landing.html',
  styleUrl: './landing.scss',
})
export class Landing {
  private readonly router = inject(Router);

  readonly categories = [
    'Livros',
    'Eletrônicos',
    'Informática',
    'Materiais acadêmicos',
    'Casa',
    'Outros',
  ];

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
}
