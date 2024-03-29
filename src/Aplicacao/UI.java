package Aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Xadrez.Cor;
import Xadrez.Partida_de_xadrez;
import Xadrez.Peca_de_xadrez;
import Xadrez.Xadrez_posicao;

public class UI {
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static Xadrez_posicao lerPosicaoXadrez(Scanner sc) {
		try {
		String s = sc.nextLine();
		char coluna = s.charAt(0);
		int linha = Integer.parseInt(s.substring(1));
		return new Xadrez_posicao(coluna,linha);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao ler entrada da posicao do xadrez. Valores validos vão de a1 ate h8.");
		}
	}
public static void printPartida(Partida_de_xadrez partida_De_Xadrez, List<Peca_de_xadrez> capturada) {
	printTabuleiro(partida_De_Xadrez.getPecas());
	System.out.println();
	mostrarPecasCapturadas(capturada);
	System.out.println();
	System.out.println("Turno: "+ partida_De_Xadrez.getTurno());
	if(!partida_De_Xadrez.getCheckMate()) {
	System.out.println("Esperando o jogador: "+ partida_De_Xadrez.getCorDoJogadorAtual());
	if(partida_De_Xadrez.getCheck()) {
		System.out.println("ESTA EM CHECK!");
	}
	}
	else {
		System.out.println("CHECKMATE!!!");
		System.out.println("Vencedor: " + partida_De_Xadrez.getCorDoJogadorAtual());
	}
	
}
	public static void printTabuleiro(Peca_de_xadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	public static void printTabuleiro(Peca_de_xadrez[][] pecas, boolean[][] movimentosPossiveis) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], movimentosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPeca(Peca_de_xadrez peca, boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-"+ ANSI_RESET);
		} else {
		
		  if (peca.getCor() == Cor.VERMELHO) {
              System.out.print(ANSI_RED + peca + ANSI_RESET);
          }
          else {
              System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
          }
		}
		System.out.print(" ");
	}
	private static void mostrarPecasCapturadas(List<Peca_de_xadrez> capturada) {
		List<Peca_de_xadrez> vermelho = capturada.stream().filter(x -> x.getCor() == Cor.VERMELHO).collect(Collectors.toList());
		List<Peca_de_xadrez> amarelo = capturada.stream().filter(x -> x.getCor() == Cor.AMARELO).collect(Collectors.toList());
		System.out.println("Pecas Capturadas:");
		System.out.print("Vermelhas: ");
		System.out.print(ANSI_RED);
		System.out.println(Arrays.toString(vermelho.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print("Amarelas: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(amarelo.toArray()));
		System.out.print(ANSI_RESET);
	}
}
	
