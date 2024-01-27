package Aplicacao;


import Xadrez.Partida_de_xadrez;

public class Programa {
	public static void main(String[] args) {
		Partida_de_xadrez partida = new Partida_de_xadrez();
		UI.printTabuleiro(partida.getPecas());
	}
}
