package Xadrez;

import Camada_de_tabuleiro.Posicao;

public class Xadrez_posicao {
private char coluna;
private int linha;

public Xadrez_posicao(char coluna, int linha) {
	if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
		throw new Xadrez_Excecao("Erro ao instanciar a Posicao do xadrez. Valores validos são de a1 á h8.");
	}
	this.coluna = coluna;
	this.linha = linha;
}

public char getColuna() {
	return coluna;
}

public int getLinha() {
	return linha;
}
protected Posicao toPosicao() {
	return new Posicao(8 - linha, coluna - 'a');
}
protected static Xadrez_posicao daPosicao(Posicao posicao) {
	return new Xadrez_posicao((char)('a' - posicao.getColuna()), 8 - posicao.getLinha());
}
@Override
public String toString() {
	return "" + coluna + linha;
}
}
