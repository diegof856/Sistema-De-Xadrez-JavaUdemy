package Xadrez;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Camada_de_tabuleiro.Pecas;
import Camada_de_tabuleiro.Posicao;
import Camada_de_tabuleiro.Tabuleiro;
import Xadrez.pecas.Bispo;
import Xadrez.pecas.Cavalo;
import Xadrez.pecas.Peao;
import Xadrez.pecas.Rainha;
import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;

public class Partida_de_xadrez {
private Cor corDoJogadorAtual;
private int turno;
private Tabuleiro tabuleiro;
private boolean check;
private boolean checkMate;
private Peca_de_xadrez enPassantVulnerável;
private Peca_de_xadrez promocao;

private List<Pecas> pecasNoTabuleiro;
private List<Pecas> pecasCapturadas;

public Partida_de_xadrez() {
	tabuleiro = new Tabuleiro(8, 8);
	turno = 1;
	corDoJogadorAtual = Cor.VERMELHO;
	pecasNoTabuleiro = new ArrayList<>();
	pecasCapturadas = new ArrayList<>();
	initialSetup();
}
public int getTurno() {
	return turno;
}
public Cor getCorDoJogadorAtual() {
	return corDoJogadorAtual;
}

public boolean getCheck() {
	return check;
}
public boolean getCheckMate() {
	return checkMate;
}
public Peca_de_xadrez getEnPassantVulnerável() {
	return enPassantVulnerável;
}
public Peca_de_xadrez getPromocao() {
	return promocao;
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
	
	if(testeDeCheck(corDoJogadorAtual)) {
		desfazerMovimento(origem, destino, pecaCapturada);
		throw new Xadrez_Excecao("Voce nao pode se colocar em check");
	}
	
	Peca_de_xadrez pecaMovida = (Peca_de_xadrez)tabuleiro.pecas(destino);
		
	//#Movimento Especial Promocao
	promocao = null;
	if(pecaMovida instanceof Peao) {
		if((pecaMovida.getCor() == Cor.VERMELHO && destino.getLinha() == 0) || (pecaMovida.getCor() == Cor.AMARELO && destino.getLinha() == 7)) {
			promocao = (Peca_de_xadrez)tabuleiro.pecas(destino);
			promocao = trocarPecaPromovida("Q");
		}
	}
	
	check = (testeDeCheck(oponente(corDoJogadorAtual))) ? true : false;
	
	if(testeDeCheckMate(oponente(corDoJogadorAtual))) {
		checkMate = true;
	}
	else {
		proximoTurno();
	}
	// #Movimento especial en passant
	
	if(pecaMovida instanceof Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
		enPassantVulnerável = pecaMovida;
	}else {
		enPassantVulnerável = null;
	}
	
	return (Peca_de_xadrez)pecaCapturada;
}

public Peca_de_xadrez trocarPecaPromovida(String type) {
	if(promocao == null) {
		throw new IllegalStateException("Nao ha peca a ser promovida");
	}
	if(!type.equals("B") && !type.equals("C") && !type.equals("T") && !type.equals("Q")) {
		throw new InvalidParameterException("Tipo invalido para promocao");
	}
	Posicao pos = promocao.getPosicaoXadrez().toPosicao();
	Pecas p = tabuleiro.removerPeca(pos);
	pecasNoTabuleiro.remove(p);
	Peca_de_xadrez novaPeca = novaPeca(type, promocao.getCor());
	tabuleiro.colocarPeca(novaPeca, pos);
	pecasNoTabuleiro.add(novaPeca);
	
	return novaPeca;
	
}

private Peca_de_xadrez novaPeca(String type, Cor cor){
	if(type.equals("B")) return new Bispo(tabuleiro, cor);
	if(type.equals("C")) return new Cavalo(tabuleiro, cor);
	if(type.equals("Q")) return new Rainha(tabuleiro, cor);
	return new Torre(tabuleiro, cor);
}
private Pecas fazerMovimento(Posicao origem, Posicao destino) {
	Peca_de_xadrez p = (Peca_de_xadrez)tabuleiro.removerPeca(origem);
	p.incrementandoAContagem();
	Pecas pecaCapturada = tabuleiro.removerPeca(destino);
	tabuleiro.colocarPeca(p, destino);
	
	if(pecaCapturada != null) {
		pecasNoTabuleiro.remove(pecaCapturada);
		pecasCapturadas.add(pecaCapturada);
	}
	//#Movimento especial Roque pequeno ao lado do rei
	if(p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
		Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
		Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
		Peca_de_xadrez torre = (Peca_de_xadrez)tabuleiro.removerPeca(origemTorre);
		tabuleiro.colocarPeca(torre, destinoTorre);
		torre.incrementandoAContagem();
				
	}
	//#Movimento especial Roque grande ao lado da rainha
		if(p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			Peca_de_xadrez torre = (Peca_de_xadrez)tabuleiro.removerPeca(origemTorre);
			tabuleiro.colocarPeca(torre, destinoTorre);
			torre.incrementandoAContagem();
					
		}
		
		// #Movimento especial en passant
		if(p instanceof Peao) {
			if(origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
				Posicao posicaoPeao;
				if(p.getCor() == Cor.VERMELHO) {
					posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				}else {
					posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
			}
		}
	return pecaCapturada;
}

private void desfazerMovimento(Posicao origem, Posicao destino, Pecas pecaCapturada) {
	Peca_de_xadrez p = (Peca_de_xadrez)tabuleiro.removerPeca(destino);
	p.decrementandoAContagem();
	tabuleiro.colocarPeca(p, origem);
	
	if(pecaCapturada != null) {
		tabuleiro.colocarPeca(pecaCapturada, destino);
		pecasCapturadas.remove(pecaCapturada);
		pecasNoTabuleiro.add(pecaCapturada);
	}
	
	//#Movimento especial Roque pequeno ao lado do rei
		if(p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			Peca_de_xadrez torre = (Peca_de_xadrez)tabuleiro.removerPeca(destinoTorre);
			tabuleiro.colocarPeca(torre, origemTorre);
			torre.decrementandoAContagem();
					
		}
		//#Movimento especial Roque grande ao lado da rainha
			if(p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
				Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
				Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
				Peca_de_xadrez torre = (Peca_de_xadrez)tabuleiro.removerPeca(destinoTorre);
				tabuleiro.colocarPeca(torre, origemTorre);
				torre.decrementandoAContagem();
						
			}

			// #Movimento especial en passant
			if(p instanceof Peao) {
				if(origem.getColuna() != destino.getColuna() && pecaCapturada == enPassantVulnerável) {
				Peca_de_xadrez peao = (Peca_de_xadrez)tabuleiro.removerPeca(destino);
					
					Posicao posicaoPeao;
					if(p.getCor() == Cor.VERMELHO) {
						posicaoPeao = new Posicao(3, destino.getColuna());
					}else {
						posicaoPeao = new Posicao(4, destino.getColuna());
					}
					tabuleiro.colocarPeca(peao, posicaoPeao);
					
				}
			}
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



private void proximoTurno() {
	turno++;
	corDoJogadorAtual = (corDoJogadorAtual == Cor.VERMELHO) ? Cor.AMARELO : Cor.VERMELHO;
}

private Cor oponente(Cor cor) {
	return (cor == Cor.VERMELHO) ? Cor.AMARELO : Cor.VERMELHO;
}

private Peca_de_xadrez rei(Cor cor) {
	List<Pecas> list = pecasNoTabuleiro.stream().filter(x -> ((Peca_de_xadrez)x).getCor() == cor).collect(Collectors.toList());
	for(Pecas p : list) {
		if (p instanceof Rei) {
			return (Peca_de_xadrez)p;
		}
	}
	throw new IllegalStateException("Nao existe um rei com essa cor "+ cor);
}

private boolean testeDeCheck(Cor cor) {
	Posicao posicaoDoRei = rei(cor).getPosicaoXadrez().toPosicao();
	List<Pecas> pecasDoOponente = pecasNoTabuleiro.stream().filter(x -> ((Peca_de_xadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
	for(Pecas p : pecasDoOponente) {
		boolean [][] mat = p.movimentosPossiveis();
		if(mat[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
			return true;
		}
	}
return false;
}
private boolean testeDeCheckMate(Cor cor) {
	if(!testeDeCheck(cor)) {
		return false;
	}
	List<Pecas> list = pecasNoTabuleiro.stream().filter(x -> ((Peca_de_xadrez)x).getCor() == cor).collect(Collectors.toList());
	for (Pecas p : list) {
		boolean [][] mat = p.movimentosPossiveis();
		for(int i= 0; i < tabuleiro.getLinhas(); i++) {
			for(int j = 0; j < tabuleiro.getColunas(); j++) {
				if(mat[i][j]) {
					Posicao origem = ((Peca_de_xadrez)p).getPosicaoXadrez().toPosicao();
					Posicao destino = new Posicao(i, j);
					Pecas pecasCapturada = fazerMovimento(origem, destino);
					boolean testeCheck = testeDeCheck(cor);
					desfazerMovimento(origem, destino, pecasCapturada);
					if(!testeCheck) {
						return false;
					}
				}
			}
		}
	}
	return true;
}


private void colocarNovaPeca(char coluna, int linha, Peca_de_xadrez peca) {
	tabuleiro.colocarPeca(peca, new Xadrez_posicao (coluna, linha).toPosicao());
	pecasNoTabuleiro.add(peca);
}

private void initialSetup(){
	colocarNovaPeca('a', 1,new Torre(tabuleiro, Cor.VERMELHO));
	colocarNovaPeca('b', 1,new Cavalo(tabuleiro, Cor.VERMELHO));
	colocarNovaPeca('c', 1,new Bispo(tabuleiro, Cor.VERMELHO));
	colocarNovaPeca('d', 1,new Rainha(tabuleiro, Cor.VERMELHO));
	colocarNovaPeca('e', 1,new Rei(tabuleiro, Cor.VERMELHO, this));
	colocarNovaPeca('f', 1,new Bispo(tabuleiro, Cor.VERMELHO));
	colocarNovaPeca('g', 1,new Cavalo(tabuleiro, Cor.VERMELHO));
	colocarNovaPeca('h', 1,new Torre(tabuleiro, Cor.VERMELHO));
	colocarNovaPeca('a', 2,new Peao(tabuleiro, Cor.VERMELHO, this));
	colocarNovaPeca('b', 2,new Peao(tabuleiro, Cor.VERMELHO, this));
	colocarNovaPeca('c', 2,new Peao(tabuleiro, Cor.VERMELHO, this));
	colocarNovaPeca('d', 2,new Peao(tabuleiro, Cor.VERMELHO, this));
	colocarNovaPeca('e', 2,new Peao(tabuleiro, Cor.VERMELHO, this));
	colocarNovaPeca('f', 2,new Peao(tabuleiro, Cor.VERMELHO, this));
	colocarNovaPeca('g', 2,new Peao(tabuleiro, Cor.VERMELHO, this));
	colocarNovaPeca('h', 2,new Peao(tabuleiro, Cor.VERMELHO, this));
	
	
	
	colocarNovaPeca('a', 8,new Torre(tabuleiro, Cor.AMARELO));
	colocarNovaPeca('b', 8,new Cavalo(tabuleiro, Cor.AMARELO));
	colocarNovaPeca('c', 8,new Bispo(tabuleiro, Cor.AMARELO));
	colocarNovaPeca('d', 8,new Rainha(tabuleiro, Cor.AMARELO));
	colocarNovaPeca('e', 8,new Rei(tabuleiro, Cor.AMARELO, this));
	colocarNovaPeca('f', 8,new Bispo(tabuleiro, Cor.AMARELO));
	colocarNovaPeca('g', 8,new Cavalo(tabuleiro, Cor.AMARELO));
	colocarNovaPeca('h', 8,new Torre(tabuleiro, Cor.AMARELO));
	colocarNovaPeca('a', 7,new Peao(tabuleiro, Cor.AMARELO, this));
	colocarNovaPeca('b', 7,new Peao(tabuleiro, Cor.AMARELO, this));
	colocarNovaPeca('c', 7,new Peao(tabuleiro, Cor.AMARELO, this));
	colocarNovaPeca('d', 7,new Peao(tabuleiro, Cor.AMARELO, this));
	colocarNovaPeca('e', 7,new Peao(tabuleiro, Cor.AMARELO, this));
	colocarNovaPeca('f', 7,new Peao(tabuleiro, Cor.AMARELO, this));
	colocarNovaPeca('g', 7,new Peao(tabuleiro, Cor.AMARELO, this));
	colocarNovaPeca('h', 7,new Peao(tabuleiro, Cor.AMARELO, this));
}
}
