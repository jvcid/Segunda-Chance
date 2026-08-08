import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { Ad, AdPage } from '../models/ad';
import { AdRequest } from '../models/ad-request';

@Injectable({
  providedIn: 'root',
})
export class AdsService {
  private readonly http = inject(HttpClient);

  private readonly apiUrl = `${environment.apiUrl}/api/anuncios`;

  getAds(
    page = 0,
    size = 4,
    titulo?: string,
    categoryId?: number,
    tipo?: 'VENDA' | 'DOACAO',
    status?: string,
  ): Observable<AdPage> {
    let params = new HttpParams().set('page', page).set('size', size);

    if (titulo) {
      params = params.set('titulo', titulo);
    }

    if (categoryId !== undefined) {
      params = params.set('categoryId', categoryId);
    }

    if (tipo) {
      params = params.set('tipo', tipo);
    }

    if (status) {
      params = params.set('status', status);
    }

    return this.http.get<AdPage>(this.apiUrl, {
      params,
    });
  }
  getAdById(id: number): Observable<Ad> {
    return this.http.get<Ad>(`${this.apiUrl}/${id}`);
  }

  getMyAds(): Observable<Ad[]> {
    return this.http.get<Ad[]>(`${this.apiUrl}/me`);
  }

  createAd(data: AdRequest): Observable<Ad> {
    return this.http.post<Ad>(this.apiUrl, data);
  }

  updateAd(id: number, data: AdRequest): Observable<Ad> {
    return this.http.put<Ad>(`${this.apiUrl}/${id}`, data);
  }

  deleteAd(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
