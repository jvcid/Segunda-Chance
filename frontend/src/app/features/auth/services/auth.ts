import { HttpClient } from '@angular/common/http';
import { Injectable, inject, signal } from '@angular/core';
import { Observable, tap } from 'rxjs';

import { LoginRequest } from '../models/login-request';
import { LoginResponse } from '../models/login-response';
import { RegisterRequest } from '../models/register-request';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly http = inject(HttpClient);

  private readonly apiUrl = 'http://localhost:8080/api/auth';
  private readonly tokenKey = 'segunda_chance_token';

  private readonly authenticated = signal(this.getToken() !== null);

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
    return this.getToken() !== null;
  }

  private saveToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }
}
