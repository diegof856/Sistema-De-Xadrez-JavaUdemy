package Xadrez;

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
private void colocarNovaPeca(char coluna, int linha, Peca_de_xadrez peca) {
	tabuleiro.colocarPeca(peca, new Xadrez_posicao (coluna, linha).toPosicao());
}
private void initialSetup(){
	colocarNovaPeca('c', 1,new Torre(tabuleiro, Cor.RED));
	colocarNovaPeca('c', 2,new Rei(tabuleiro, Cor.RED));
	colocarNovaPeca('d', 2,new Torre(tabuleiro, Cor.RED));
	colocarNovaPeca('e', 2,new Torre(tabuleiro, Cor.RED));
	colocarNovaPeca('e', 1,new Rei(tabuleiro, Cor.RED));
	colocarNovaPeca('d', 1,new Torre(tabuleiro, Cor.RED));
	
	colocarNovaPeca('c', 7,new Torre(tabuleiro, Cor.YELLOW));
	colocarNovaPeca('c', 8,new Rei(tabuleiro, Cor.YELLOW));
	colocarNovaPeca('d', 7,new Torre(tabuleiro, Cor.YELLOW));
	colocarNovaPeca('e', 7,new Torre(tabuleiro, Cor.YELLOW));
	colocarNovaPeca('e', 8,new Rei(tabuleiro, Cor.YELLOW));
	colocarNovaPeca('d', 8,new Torre(tabuleiro, Cor.YELLOW));
}
}
