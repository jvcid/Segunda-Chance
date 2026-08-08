export type AdType = 'VENDA' | 'DOACAO';

export interface AdRequest {
  titulo: string;
  descricao: string;
  tipo: AdType;
  preco: number | null;
  categoryId: number;
}
