import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

import { Header } from '../../../../shared/components/layout/header/header';

@Component({
  selector: 'app-requests-dashboard',
  imports: [Header, RouterLink],
  templateUrl: './requests-dashboard.html',
  styleUrl: './requests-dashboard.scss',
})
export class RequestsDashboard {}
