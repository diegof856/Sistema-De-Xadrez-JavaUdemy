package Xadrez.pecas;

import Camada_de_tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.Peca_de_xadrez;

public class Torre extends Peca_de_xadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
@Override
public String toString() {
	return "T";
}
}
