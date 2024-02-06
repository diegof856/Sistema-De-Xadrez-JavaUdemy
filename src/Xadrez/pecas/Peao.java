package Xadrez.pecas;

import Camada_de_tabuleiro.Posicao;
import Camada_de_tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.Partida_de_xadrez;
import Xadrez.Peca_de_xadrez;

public class Peao extends Peca_de_xadrez {
	private Partida_de_xadrez partidaXadrez;
	
	public Peao(Tabuleiro tabuleiro, Cor cor, Partida_de_xadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
		
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		if(getCor() == Cor.VERMELHO) {
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().haPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().haPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().haPeca(p2) && getContagemDeMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if(getTabuleiro().posicaoExiste(p) && haPecasDoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if(getTabuleiro().posicaoExiste(p) && haPecasDoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			// #Movimento especial en passant peças vermelhas
			if(posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if(getTabuleiro().posicaoExiste(esquerda) && haPecasDoOponente(esquerda) && getTabuleiro().pecas(esquerda) == partidaXadrez.getEnPassantVulnerável()) {
					mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().posicaoExiste(direita) && haPecasDoOponente(direita) && getTabuleiro().pecas(direita) == partidaXadrez.getEnPassantVulnerável()) {
					mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}
		}else {
			p.setValores(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().haPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().haPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().haPeca(p2) && getContagemDeMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if(getTabuleiro().posicaoExiste(p) && haPecasDoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if(getTabuleiro().posicaoExiste(p) && haPecasDoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			// #Movimento especial en passant peças amarelas
						if(posicao.getLinha() == 4) {
							Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
							if(getTabuleiro().posicaoExiste(esquerda) && haPecasDoOponente(esquerda) && getTabuleiro().pecas(esquerda) == partidaXadrez.getEnPassantVulnerável()) {
								mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
							}
							Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
							if(getTabuleiro().posicaoExiste(direita) && haPecasDoOponente(direita) && getTabuleiro().pecas(direita) == partidaXadrez.getEnPassantVulnerável()) {
								mat[direita.getLinha() + 1][direita.getColuna()] = true;
							}
						}
		}
		
		return mat;
	}
	@Override
	public String toString() {
		return "P";
	}

}
