package Camada_de_tabuleiro;

public class Posicao {
private int linhas;
private int colunas;

public Posicao(int linhas, int colunas) {

	this.linhas = linhas;
	this.colunas = colunas;
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

	@Override
public String toString() {
		return linhas + ", "+ colunas;
	}

}
