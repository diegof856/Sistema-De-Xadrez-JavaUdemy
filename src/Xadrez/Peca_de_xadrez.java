package Xadrez;

import Camada_de_tabuleiro.Pecas;
import Camada_de_tabuleiro.Posicao;
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
public Xadrez_posicao getPosicaoXadrez() {
	return Xadrez_posicao.daPosicao(posicao);
}
protected boolean haPecasDoOponente(Posicao posicao) {
	Peca_de_xadrez p = (Peca_de_xadrez)getTabuleiro().pecas(posicao);
	return p != null && p.getCor() != cor;
}
 
 
}
