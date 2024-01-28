package Aplicacao;


import java.util.Scanner;

import Xadrez.Partida_de_xadrez;
import Xadrez.Peca_de_xadrez;
import Xadrez.Xadrez_posicao;

public class Programa {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		Partida_de_xadrez partida = new Partida_de_xadrez();
		while(true) {
		UI.printTabuleiro(partida.getPecas());
		System.out.println();
		System.out.print("Origem: ");
		Xadrez_posicao origem = UI.lerPosicaoXadrez(sc);
		System.out.println();
		System.out.print("Target: ");
		Xadrez_posicao destino = UI.lerPosicaoXadrez(sc);
		
		
				Peca_de_xadrez pecaCapturada = partida.executarMovimentoDePeca(origem,destino);
		
		}
	}
}
