export interface Request {
  id: number;
  mensagem: string;
  status: string;
  createdAt: string;

  anuncioId: number;
  anuncioTitulo: string;

  userId: number;
  userName: string;
}
