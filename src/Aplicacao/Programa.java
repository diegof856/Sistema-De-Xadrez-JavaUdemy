package Aplicacao;


import java.util.InputMismatchException;
import java.util.Scanner;

import Xadrez.Partida_de_xadrez;
import Xadrez.Peca_de_xadrez;
import Xadrez.Xadrez_Excecao;
import Xadrez.Xadrez_posicao;

public class Programa {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		Partida_de_xadrez partida = new Partida_de_xadrez();
		while(true) {
			try {
			UI.limparTela();
		UI.printPartida(partida);
		System.out.println();
		System.out.print("Origem: ");
		Xadrez_posicao origem = UI.lerPosicaoXadrez(sc);
		
		boolean[][] movimentoPossivel = partida.movimentosPossiveis(origem);
		UI.limparTela();
		UI.printTabuleiro(partida.getPecas(), movimentoPossivel);
		
		System.out.println();
		System.out.print("Destino: ");
		Xadrez_posicao destino = UI.lerPosicaoXadrez(sc);
		
		
				Peca_de_xadrez pecaCapturada = partida.executarMovimentoDePeca(origem,destino);
			}
			catch(Xadrez_Excecao e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}
