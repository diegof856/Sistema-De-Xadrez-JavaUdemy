package Xadrez.pecas;

import Camada_de_tabuleiro.Posicao;
import Camada_de_tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.Peca_de_xadrez;

public class Bispo extends Peca_de_xadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
@Override
public String toString() {
	return "B";
}
public boolean[][] movimentosPossiveis() {
    boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

    Posicao p = new Posicao(0, 0);

    // Noroeste
    p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
    while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haPeca(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
        p.setValores(p.getLinha() - 1, p.getColuna() - 1);
    }
    if (getTabuleiro().posicaoExiste(p) && haPecasDoOponente(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
    }

    // Nordeste
    p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
    while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haPeca(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
        p.setValores(p.getLinha() - 1, p.getColuna() + 1);
    }
    if (getTabuleiro().posicaoExiste(p) && haPecasDoOponente(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
    }

    // Sudeste
    p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
    while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haPeca(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
        p.setValores(p.getLinha() + 1, p.getColuna() + 1);
    }
    if (getTabuleiro().posicaoExiste(p) && haPecasDoOponente(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
    }

    // Sudoeste
    p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
    while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haPeca(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
         p.setValores(p.getLinha() + 1, p.getColuna() - 1);
    }
    if (getTabuleiro().posicaoExiste(p) && haPecasDoOponente(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
    }

    return mat;
}

}
