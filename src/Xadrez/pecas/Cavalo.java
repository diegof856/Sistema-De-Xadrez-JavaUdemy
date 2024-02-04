package Xadrez.pecas;

import Camada_de_tabuleiro.Posicao;
import Camada_de_tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.Peca_de_xadrez;

public class Cavalo extends Peca_de_xadrez {

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
@Override
public String toString() {
	return "C";
}
private boolean podeMover(Posicao posicao) {
	Peca_de_xadrez p = (Peca_de_xadrez)getTabuleiro().pecas(posicao);
	return p == null || p.getCor() != getCor();
}
@Override
public boolean[][] movimentosPossiveis() {
	boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
	
	Posicao p = new Posicao(0, 0);
	
	p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
	if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
	}
	
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
	
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
				p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
				if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
		
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
		}
	return mat;
}


}
