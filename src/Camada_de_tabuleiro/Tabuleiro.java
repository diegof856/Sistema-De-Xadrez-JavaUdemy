package Camada_de_tabuleiro;

public class Tabuleiro {
	
private int linhas;
private int colunas;
private Pecas[][] pecas;

public Tabuleiro(int linhas, int colunas) {
	
	this.linhas = linhas;
	this.colunas = colunas;
	pecas = new Pecas[linhas][colunas];
}

public int getLinhas() {
	return linhas;
}

public void setLinhas(int linhas) {
	this.linhas = linhas;
}

public int getColunas() {
	return colunas;
}

public void setColunas(int colunas) {
	this.colunas = colunas;
}

public Pecas pecas(int linhas, int colunas) {
	return pecas[linhas][colunas];
}
public Pecas pecas(Posicao posicao) {
	return pecas[posicao.getLinha()][posicao.getColuna()];
}
public void colocarPeca(Pecas peca, Posicao posicao) {
	pecas[posicao.getLinha()][posicao.getColuna()] = peca;
	peca.posicao = posicao;
}
}
