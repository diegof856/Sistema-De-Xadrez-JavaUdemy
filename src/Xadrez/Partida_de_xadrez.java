package Xadrez;

import Camada_de_tabuleiro.Posicao;
import Camada_de_tabuleiro.Tabuleiro;
import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;

public class Partida_de_xadrez {
	
private Tabuleiro tabuleiro;

public Partida_de_xadrez() {
	tabuleiro = new Tabuleiro(8, 8);
	initialSetup();
}
public Peca_de_xadrez[][] getPecas(){
	Peca_de_xadrez[][] mat = new Peca_de_xadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
	for (int i=0; i<tabuleiro.getLinhas(); i++) {
		for(int j=0;j<tabuleiro.getColunas();j++) {
			mat[i][j] = (Peca_de_xadrez) tabuleiro.pecas(i,j);
		}
	}
	return mat;
}
private void initialSetup(){
	tabuleiro.colocarPeca(new Torre(tabuleiro, Cor.BLACK), new Posicao(2,1));
	tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.RED), new Posicao(0,5));
	tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.BLACK), new Posicao(7,4));
}
}
