import { Component, inject, signal } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.scss',
})
export class Register {
  private readonly fb = inject(FormBuilder);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  readonly isLoading = signal(false);
  readonly errorMessage = signal('');

  readonly registerForm = this.fb.nonNullable.group(
    {
      name: ['', [Validators.required, Validators.maxLength(100)]],

      email: ['', [Validators.required, Validators.email]],

      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(100)]],

      confirmPassword: ['', Validators.required],
    },
    {
      validators: [this.passwordMatchValidator()],
    },
  );

  onSubmit(): void {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    this.isLoading.set(true);
    this.errorMessage.set('');

    const formValue = this.registerForm.getRawValue();

    const request = {
      name: formValue.name,
      email: formValue.email,
      password: formValue.password,
    };

    this.authService.register(request).subscribe({
      next: () => {
        this.isLoading.set(false);

        this.router.navigate(['/login'], {
          queryParams: {
            registered: 'true',
          },
        });
      },

      error: (error) => {
        this.isLoading.set(false);

        if (error.status === 409) {
          this.errorMessage.set('Já existe uma conta cadastrada com este e-mail.');
          return;
        }

        if (error.status === 400) {
          this.errorMessage.set(
            'Não foi possível realizar o cadastro. Verifique os dados informados.',
          );
          return;
        }

        this.errorMessage.set('Não foi possível criar sua conta. Tente novamente.');
      },
    });
  }

  private passwordMatchValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const password = control.get('password')?.value;
      const confirmPassword = control.get('confirmPassword')?.value;

      if (!password || !confirmPassword) {
        return null;
      }

      return password === confirmPassword ? null : { passwordMismatch: true };
    };
  }
}
