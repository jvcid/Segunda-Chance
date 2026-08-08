import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { AdImageRequest } from '../models/ad-image-request';

@Injectable({
  providedIn: 'root',
})
export class AdImagesService {
  private readonly http = inject(HttpClient);

  private readonly apiUrl = 'http://localhost:8080/api/imagens-anuncio';

  create(data: AdImageRequest): Observable<unknown> {
    return this.http.post(this.apiUrl, data);
  }
}
