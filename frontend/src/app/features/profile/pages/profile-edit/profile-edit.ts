import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { UsersService } from '../../services/users';
import { User } from '../../models/user';
import { UserRequest } from '../../models/user-request';

import { Header } from '../../../../shared/components/layout/header/header';

@Component({
  selector: 'app-profile-edit',
  imports: [Header, RouterLink, ReactiveFormsModule],
  templateUrl: './profile-edit.html',
  styleUrl: './profile-edit.scss',
})
export class ProfileEdit {
  private readonly fb = inject(FormBuilder);
  private readonly usersService = inject(UsersService);
  private readonly router = inject(Router);

  readonly user = signal<User | null>(null);

  readonly isLoading = signal(true);
  readonly isSubmitting = signal(false);

  readonly hasError = signal(false);
  readonly errorMessage = signal('');

  readonly showPassword = signal(false);

  readonly form = this.fb.nonNullable.group({
    name: ['', [Validators.required, Validators.maxLength(45)]],

    email: ['', [Validators.required, Validators.email, Validators.maxLength(60)]],

    password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(100)]],
  });

  constructor() {
    this.loadUser();
  }

  loadUser(): void {
    this.isLoading.set(true);
    this.hasError.set(false);

    this.usersService.getMe().subscribe({
      next: (user) => {
        this.user.set(user);

        this.form.patchValue({
          name: user.name,
          email: user.email,
          password: '',
        });

        this.isLoading.set(false);
      },

      error: (error) => {
        console.error('Erro ao carregar perfil:', error);

        this.hasError.set(true);

        this.errorMessage.set('Não foi possível carregar os dados do perfil.');

        this.isLoading.set(false);
      },
    });
  }

  togglePassword(): void {
    this.showPassword.update((visible) => !visible);
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const currentUser = this.user();

    if (!currentUser) {
      return;
    }

    const value = this.form.getRawValue();

    const request: UserRequest = {
      name: value.name.trim(),
      email: value.email.trim(),
      password: value.password,
      roleId: currentUser.roleId,
    };

    this.isSubmitting.set(true);
    this.hasError.set(false);
    this.errorMessage.set('');

    this.usersService.update(currentUser.id, request).subscribe({
      next: () => {
        this.isSubmitting.set(false);

        this.router.navigate(['/profile']);
      },

      error: (error) => {
        console.error('Erro ao atualizar perfil:', error);

        this.isSubmitting.set(false);
        this.hasError.set(true);

        this.errorMessage.set(
          error.error?.message ??
            'Não foi possível atualizar o perfil. Verifique os dados e tente novamente.',
        );
      },
    });
  }
}
