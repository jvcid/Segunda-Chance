import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Request } from '../models/request';
import { RequestCreate } from '../models/request-create';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class RequestsService {
  private readonly http = inject(HttpClient);

  private readonly apiUrl = `${environment.apiUrl}/api/solicitacoes`;

  create(data: RequestCreate): Observable<Request> {
    return this.http.post<Request>(this.apiUrl, data);
  }

  getSent(): Observable<Request[]> {
    return this.http.get<Request[]>(`${this.apiUrl}/enviadas`);
  }

  getReceived(): Observable<Request[]> {
    return this.http.get<Request[]>(`${this.apiUrl}/recebidas`);
  }

  getById(id: number): Observable<Request> {
    return this.http.get<Request>(`${this.apiUrl}/${id}`);
  }

  approve(id: number): Observable<Request> {
    return this.http.patch<Request>(`${this.apiUrl}/${id}/aprovar`, {});
  }

  reject(id: number): Observable<Request> {
    return this.http.patch<Request>(`${this.apiUrl}/${id}/rejeitar`, {});
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
