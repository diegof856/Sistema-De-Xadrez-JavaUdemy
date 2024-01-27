package Xadrez;

import Camada_de_tabuleiro.Tabuleiro;

public class Partida_de_xadrez {
	
private Tabuleiro tabuleiro;

public Partida_de_xadrez() {
	tabuleiro = new Tabuleiro(8, 8);
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
}
