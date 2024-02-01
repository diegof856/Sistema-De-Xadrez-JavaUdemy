package Xadrez;

import java.util.ArrayList;
import java.util.List;

import Camada_de_tabuleiro.Pecas;
import Camada_de_tabuleiro.Posicao;
import Camada_de_tabuleiro.Tabuleiro;
import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;

public class Partida_de_xadrez {
private Cor corDoJogadorAtual;
private int turno;
private Tabuleiro tabuleiro;

private List<Pecas> pecasNoTabuleiro = new ArrayList<>();
private List<Pecas> pecasCapturadas = new ArrayList<>();

public Partida_de_xadrez() {
	tabuleiro = new Tabuleiro(8, 8);
	turno = 1;
	corDoJogadorAtual = Cor.VERMELHO;
	initialSetup();
}
public int getTurno() {
	return turno;
}
public Cor getCorDoJogadorAtual() {
	return corDoJogadorAtual;
}

public boolean[][] movimentosPossiveis(Xadrez_posicao posicaoOrigem){
	Posicao posicao = posicaoOrigem.toPosicao();
	validarPosicaoOrigem(posicao);
	return tabuleiro.pecas(posicao).movimentosPossiveis();
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

public Peca_de_xadrez executarMovimentoDePeca(Xadrez_posicao posicaoDeOrigem, Xadrez_posicao posicaoDeDestino) {
	Posicao origem = posicaoDeOrigem.toPosicao();
	Posicao destino = posicaoDeDestino.toPosicao();
	validarPosicaoOrigem(origem);
	validarPosicaoDestino(origem, destino);
	Pecas pecaCapturada = fazerMovimento(origem, destino);
	proximoTurno();
	return (Peca_de_xadrez)pecaCapturada;
}
private void validarPosicaoOrigem(Posicao posicao) {
	if(!tabuleiro.haPeca(posicao)) {
		throw new Xadrez_Excecao("Nao existe peca na posicao de origem");
	}
	if(corDoJogadorAtual != ((Peca_de_xadrez)tabuleiro.pecas(posicao)).getCor()) {
		throw new Xadrez_Excecao("A peca escolhica nao e sua!!!!");
	}
	if(!tabuleiro.pecas(posicao).ePossivelTerUmMovimento()) {
		throw new Xadrez_Excecao("Nao existe movimentos possiveis para esta peca");
	}
}
private void validarPosicaoDestino(Posicao origem, Posicao destino) {
	if(!tabuleiro.pecas(origem).movimentoPossivel(destino)) {
		throw new Xadrez_Excecao("Ha peca escolhida nao pode se mover para a posicao de destino");
	}
}

private Pecas fazerMovimento(Posicao origem, Posicao destino) {
	Pecas p = tabuleiro.removerPeca(origem);
	Pecas pecaCapturada = tabuleiro.removerPeca(destino);
	tabuleiro.colocarPeca(p, destino);
	
	if(pecaCapturada != null) {
		pecasNoTabuleiro.remove(pecaCapturada);
		pecasCapturadas.add(pecaCapturada);
	}
	
	return pecaCapturada;
}

private void proximoTurno() {
	turno++;
	corDoJogadorAtual = (corDoJogadorAtual == Cor.VERMELHO) ? Cor.AMARELO : Cor.VERMELHO;
}

private void colocarNovaPeca(char coluna, int linha, Peca_de_xadrez peca) {
	tabuleiro.colocarPeca(peca, new Xadrez_posicao (coluna, linha).toPosicao());
	pecasNoTabuleiro.add(peca);
}

private void initialSetup(){
	colocarNovaPeca('c', 1,new Torre(tabuleiro, Cor.VERMELHO));
	colocarNovaPeca('c', 2,new Rei(tabuleiro, Cor.VERMELHO));
	colocarNovaPeca('d', 2,new Torre(tabuleiro, Cor.VERMELHO));
	colocarNovaPeca('e', 2,new Torre(tabuleiro, Cor.VERMELHO));
	colocarNovaPeca('e', 1,new Rei(tabuleiro, Cor.VERMELHO));
	colocarNovaPeca('d', 1,new Torre(tabuleiro, Cor.VERMELHO));
	
	colocarNovaPeca('c', 7,new Torre(tabuleiro, Cor.AMARELO));
	colocarNovaPeca('c', 8,new Rei(tabuleiro, Cor.AMARELO));
	colocarNovaPeca('d', 7,new Torre(tabuleiro, Cor.AMARELO));
	colocarNovaPeca('e', 7,new Torre(tabuleiro, Cor.AMARELO));
	colocarNovaPeca('e', 8,new Rei(tabuleiro, Cor.AMARELO));
	colocarNovaPeca('d', 8,new Torre(tabuleiro, Cor.AMARELO));
}
}
