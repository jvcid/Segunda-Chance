export type AdType = 'VENDA' | 'DOACAO';

export interface Ad {
  id: number;

  titulo: string;
  descricao: string;

  tipo: AdType;

  preco: number | null;

  status: string;

  createdAt: string;

  categoryId: number;
  categoryName: string;

  userId: number;
  userName: string;

  imagemPrincipalUrl: string | null;
}

export interface AdPage {
  content: Ad[];

  empty: boolean;

  first: boolean;
  last: boolean;

  number: number;
  numberOfElements: number;

  size: number;

  totalElements: number;
  totalPages: number;

  pageable?: {
    offset: number;
    pageNumber: number;
    pageSize: number;
    paged: boolean;
    unpaged: boolean;
  };

  sort?: {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
  };
}
