package Xadrez.pecas;

import Camada_de_tabuleiro.Posicao;
import Camada_de_tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.Peca_de_xadrez;

public class Torre extends Peca_de_xadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
@Override
public String toString() {
	return "T";
}
public boolean[][] movimentosPossiveis() {
    boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

    Posicao p = new Posicao(0, 0);

    // Verificar acima
    p.setValores(posicao.getLinha() - 1, posicao.getColuna());
    while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haPeca(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
        p.setLinha(p.getLinha() - 1);
    }
    if (getTabuleiro().posicaoExiste(p) && haPecasDoOponente(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
    }

    // Esquerda
    p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
    while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haPeca(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
        p.setColuna(p.getColuna() - 1);
    }
    if (getTabuleiro().posicaoExiste(p) && haPecasDoOponente(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
    }

    // Direita
    p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
    while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haPeca(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
        p.setColuna(p.getColuna() + 1);
    }
    if (getTabuleiro().posicaoExiste(p) && haPecasDoOponente(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
    }

    // Para Baixo
    p.setValores(posicao.getLinha() + 1, posicao.getColuna());
    while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haPeca(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
        p.setLinha(p.getLinha() + 1);
    }
    if (getTabuleiro().posicaoExiste(p) && haPecasDoOponente(p)) {
        mat[p.getLinha()][p.getColuna()] = true;
    }

    return mat;
}

}
