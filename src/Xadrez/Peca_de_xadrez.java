package Xadrez;

import Camada_de_tabuleiro.Pecas;
import Camada_de_tabuleiro.Posicao;
import Camada_de_tabuleiro.Tabuleiro;

public abstract class Peca_de_xadrez extends Pecas {
 private Cor cor;
 private int contagemDeMovimento;

public Peca_de_xadrez(Tabuleiro tabuleiro, Cor cor) {
	super(tabuleiro);
	this.cor = cor;
}

public Cor getCor() {
	return cor;
}
public int getContagemDeMovimento() {
	return contagemDeMovimento;
}
public void incrementandoAContagem() {
	contagemDeMovimento ++;
}
public void decrementandoAContagem() {
	contagemDeMovimento --;
}

public Xadrez_posicao getPosicaoXadrez() {
	return Xadrez_posicao.daPosicao(posicao);
}
protected boolean haPecasDoOponente(Posicao posicao) {
	Peca_de_xadrez p = (Peca_de_xadrez)getTabuleiro().pecas(posicao);
	return p != null && p.getCor() != cor;
}
 
 
}
