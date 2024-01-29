package Xadrez;

import Camada_de_tabuleiro.Pecas;
import Camada_de_tabuleiro.Tabuleiro;

public abstract class Peca_de_xadrez extends Pecas {
 private Cor cor;

public Peca_de_xadrez(Tabuleiro tabuleiro, Cor cor) {
	super(tabuleiro);
	this.cor = cor;
}

public Cor getCor() {
	return cor;
}

 
 
}
