package Xadrez.pecas;

import Camada_de_tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.Peca_de_xadrez;

public class Rei extends Peca_de_xadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
@Override
public String toString() {
	return "R";
}
}
