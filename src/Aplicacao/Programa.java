package Aplicacao;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Xadrez.Partida_de_xadrez;
import Xadrez.Peca_de_xadrez;
import Xadrez.Xadrez_Excecao;
import Xadrez.Xadrez_posicao;

public class Programa {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		Partida_de_xadrez partida = new Partida_de_xadrez();
		List<Peca_de_xadrez> capturada = new ArrayList<>();
		while(!partida.getCheckMate()) {
			try {
			UI.limparTela();
		UI.printPartida(partida, capturada);
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
				
				if(pecaCapturada != null) {
					capturada.add(pecaCapturada);
				}
				if(partida.getPromocao() != null) {
					System.out.print("Entre com a letra da peca para a promocao (B/C/T/Q): ");
					String type = sc.nextLine().toUpperCase();
					
					while(!type.equals("B") && !type.equals("C") && !type.equals("T") && !type.equals("Q")) {
						System.out.print("Valor invalido!! Entre com a letra da peca para a promocao (B/C/T/Q): ");
						type = sc.nextLine().toUpperCase();
					}
					partida.trocarPecaPromovida(type);
				}
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
		UI.limparTela();
		UI.printPartida(partida, capturada);
	}
}
