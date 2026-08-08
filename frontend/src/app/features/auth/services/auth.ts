import { HttpClient } from '@angular/common/http';
import { Injectable, inject, signal } from '@angular/core';
import { Observable, tap } from 'rxjs';

import { environment } from '../../../../environments/environment';

import { LoginRequest } from '../models/login-request';
import { LoginResponse } from '../models/login-response';
import { RegisterRequest } from '../models/register-request';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly http = inject(HttpClient);

  private readonly apiUrl = `${environment.apiUrl}/api/auth`;

  private readonly tokenKey = 'segunda_chance_token';

  private readonly authenticated = signal(this.hasValidToken());

  readonly isLoggedIn = this.authenticated.asReadonly();

  login(credentials: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, credentials).pipe(
      tap((response) => {
        this.saveToken(response.token);
        this.authenticated.set(true);
      }),
    );
  }

  register(data: RegisterRequest): Observable<unknown> {
    return this.http.post(`${this.apiUrl}/register`, data);
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);

    this.authenticated.set(false);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isAuthenticated(): boolean {
    return this.hasValidToken();
  }

  private saveToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  private hasValidToken(): boolean {
    const token = this.getToken();

    if (!token) {
      return false;
    }

    try {
      const payloadBase64 = token.split('.')[1];

      if (!payloadBase64) {
        this.logout();
        return false;
      }

      const payloadJson = atob(payloadBase64.replace(/-/g, '+').replace(/_/g, '/'));

      const payload = JSON.parse(payloadJson);

      const expiration = payload.exp as number | undefined;

      if (!expiration) {
        this.logout();
        return false;
      }

      const currentTime = Math.floor(Date.now() / 1000);

      if (expiration <= currentTime) {
        this.logout();
        return false;
      }

      return true;
    } catch {
      this.logout();
      return false;
    }
  }
}
