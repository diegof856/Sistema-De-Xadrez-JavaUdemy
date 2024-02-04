package Xadrez.pecas;

import Camada_de_tabuleiro.Posicao;
import Camada_de_tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.Partida_de_xadrez;
import Xadrez.Peca_de_xadrez;

public class Rei extends Peca_de_xadrez {
private Partida_de_xadrez partidaDeXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, Partida_de_xadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
	}
@Override
public String toString() {
	return "R";
}
private boolean podeMover(Posicao posicao) {
	Peca_de_xadrez p = (Peca_de_xadrez)getTabuleiro().pecas(posicao);
	return p == null || p.getCor() != getCor();
}
private boolean testeTorreRoque(Posicao posicao) {
	Peca_de_xadrez p = (Peca_de_xadrez)getTabuleiro().pecas(posicao);
	return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContagemDeMovimento() == 0;
}
@Override
public boolean[][] movimentosPossiveis() {
	boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
	
	Posicao p = new Posicao(0, 0);
	//acima
	p.setValores(posicao.getLinha() - 1, posicao.getColuna());
	if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
	}
	//abaixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//direita
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//noroeste
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//nordeste
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//sudoeste
				p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
				if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
		//sudeste
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//#Movimento especial Roque
		if(getContagemDeMovimento() == 0 && !partidaDeXadrez.getCheck()) {
			//#Movimento especial Roque pequeno ao lado do rei
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if(testeTorreRoque(posT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if(getTabuleiro().pecas(p1) == null && getTabuleiro().pecas(p2) == null) {
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
				
			}
			//#Movimento especial Roque grande ao lado da rainha
			Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if(testeTorreRoque(posT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if(getTabuleiro().pecas(p1) == null && getTabuleiro().pecas(p2) == null && getTabuleiro().pecas(p3) == null) {
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
				
			}
		}
		
	return mat;
}


}
