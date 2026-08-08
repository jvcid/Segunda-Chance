import { Component, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';

import { UsersService } from '../../services/users';
import { User } from '../../models/user';

import { Header } from '../../../../shared/components/layout/header/header';

@Component({
  selector: 'app-profile',
  imports: [Header, RouterLink],
  templateUrl: './profile.html',
  styleUrl: './profile.scss',
})
export class Profile {
  private readonly usersService = inject(UsersService);

  readonly user = signal<User | null>(null);

  readonly isLoading = signal(true);
  readonly hasError = signal(false);

  constructor() {
    this.loadUser();
  }

  loadUser(): void {
    this.isLoading.set(true);
    this.hasError.set(false);

    this.usersService.getMe().subscribe({
      next: (user) => {
        this.user.set(user);
        this.isLoading.set(false);
      },

      error: (error) => {
        console.error('Erro ao carregar perfil:', error);

        this.hasError.set(true);
        this.isLoading.set(false);
      },
    });
  }

  initials(name: string): string {
    return name
      .split(' ')
      .filter(Boolean)
      .slice(0, 2)
      .map((part) => part[0])
      .join('')
      .toUpperCase();
  }
}
