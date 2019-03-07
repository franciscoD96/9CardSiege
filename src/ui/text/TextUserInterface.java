
package ui.text;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import logicaJogo.ModelGame;
import model.states.escolhaLinhadoInimigo;
import model.states.escolheAcao;
import model.states.escolhePosicaoTunel;
import model.states.esperaCompraAP;
import model.states.esperaPorCarta;
import model.states.gameOver;
import model.states.terminarPrograma;

public class TextUserInterface {
	static boolean inicioJogo = true; // mudar no estado gameover
	private ModelGame g;    
	private Scanner s;

	public TextUserInterface(ModelGame game) {
		g = game;
		s = new Scanner(System.in);
	}

	private void showGame() {
		System.out.println(g);
	}
       
	private void getUserInputWaitForCard() { 
                File f = new File(GuardaJogo.nomeFicheiro);
		int opcao = 0;
		System.out.println("Evento: " + g.getCardsiege().getEventoAtual()) ;
		System.out.println("1- Lançar uma carta");
                if(f.exists() && inicioJogo ) {
			System.out.println("2- Carregar Jogo");
		}else if(!f.exists() && inicioJogo){
			 System.out.println("Nao existe jogo guardado");
		}


		try {
			while(!s.hasNextInt()) s.next(); opcao = s.nextInt();
		} catch (InputMismatchException naoescreveuinteiro){
			System.out.println("[ERRO] Valor Inválido\n");
			s.nextLine();
			for(int i = 0; i < 2; i++) System.out.println();
		}
		if (opcao == 1){
                    inicioJogo = false;
                    g.lancamentoCarta();
                }
                if (opcao == 2 && f.exists() && inicioJogo) {
                    inicioJogo = false;
			g = GuardaJogo.carregarJogo();
                }

	}
	
	private void getUserInputAction() {

		int opcao = -1; 


		while (opcao < 0 || opcao > 9) {
			
			System.out.println("\t-:- Evento atual -:-");
			System.out.print(g.getCardsiege().imprimeEventoAtual());
			System.out.println("\t-:-              -:-\n");
			showGame();
			System.out.println();
			System.out.println("Tem " + g.verificaPontosAcao() + " Ponto(s) de Ação ainda disponíveis");
			System.out.println();
			System.out.println("1-Ataque arqueiros");
			System.out.println("2-Ataque de óleo escaldante");
			System.out.println("3-Ataque corpo-a-corpo");
			System.out.println("4-Reparar a muralha");
			System.out.println("5-Fazer um discurso motivacional");
			System.out.println("6-Movimentar tropas no tunel");
			System.out.println("7-Roubar mantimentos");
			System.out.println("8-Sabotar o inimigo");
            System.out.println("9-Guardar o Jogo");
			System.out.println("0-Passar o turno");
			System.out.print("\t-> ");

			try {
				while(!s.hasNextInt()) s.next(); opcao = s.nextInt();
			} catch (InputMismatchException naoescreveuinteiro){
				System.out.println("[ERRO] Valor Inválido\n");
				s.nextLine();
				for(int i = 0; i < 2; i++) System.out.println();
			}
		}

		switch (opcao) {
		case 0:
			g.passarTurno();							// voltar atrás.
			break;
		case 1:
                        g.escolhaLinhadoInimigo(1);
			break;
		case 2:
                        g.escolhaLinhadoInimigo(2);
			break;
		case 3:
                        g.escolhaLinhadoInimigo(3);
			break;
		case 4:
                        g.repararMuralha();//como fazer a verificao disto?
				System.out.println("O lançamento do dado deu: " + g.getUltimoLancamento());
			break;
		case 5:
                        g.discursoMotivacional();//verificacao
				System.out.println("O lançamento do dado deu: " + g.getUltimoLancamento());
			
													// achas mesmo que se justifica mais uma função?
			while (opcao < 1 || opcao > 2) {
				System.out.println("Deseja oferecer mantimentos ao seu povo \npara aumentar a moral?");
				System.out.println("1-Sim || 2-Não");
				System.out.println(" -> ");

				try {
					while(!s.hasNextInt()) s.next(); opcao = s.nextInt();
				} catch (InputMismatchException naoescreveuinteiro){
					System.out.println("[ERRO] Valor Inválido\n");
					s.nextLine();
					for(int i = 0 ; i < 2; i++) System.out.println();
				}
				switch (opcao) {
				case 1:
					g.menosProvisoesPorMaisMoral();
					break;
				case 2:
					break;
				}
			}
			break;
		case 6:
			g.escolhePosicaoTunel();
			break;
		case 7:
			g.roubarMantimentos();
			break;
		case 8:
			g.sabotarInimigo();
			break;
                case 9:
			GuardaJogo.guardarJogo(g);
			break;
		default:
			System.out.println("oops! Erro na escolha de ação");
		}
	}

	private void getUserEnemyLineChoice () {

		int opcao=-1;

		while (opcao < 0 || opcao > 3) {
			System.out.println();
			System.out.println("Que inimigo deseja atacar? ");
			System.out.println("1-Escada");
			System.out.println("2-Ariete");
			System.out.println("3-Torre");
			System.out.println("0-Voltar atras");

			try {
				while(!s.hasNextInt()) s.next(); opcao = s.nextInt();
			} catch (InputMismatchException naoescreveuinteiro){
				System.out.println("[ERRO] Valor Invalido\n");
				s.nextLine();
				for(int i = 0 ; i < 2; i++) System.out.println();
			}

			if (opcao == 0) {//certo
				g.voltaEscolheAcao();
				System.out.println("\n\n\n\n\n");
				return;
			}
                        
			switch(g.getEscolha()) { // descobre qual o ataque a realizar
			case 1:
                            //sem feedback
                            //falta a verificação... como fazer?
                            g.ataqueArqueiros(opcao);
                            break;
			case 2:
                            //sem feedback
                            //falta a verificação... como fazer?
                            g.ataqueAguaQuente(opcao);
                            break;
			case 3:
                            //sem feedback
                            //falta a verificação... como fazer?
                            g.ataqueCorpoACorpo(opcao);
                            break;
			default:
				System.out.println("What? algo está male.");
				break;
			}

			System.out.println("\n\n\n\n\n");
		}

		// quando sai daqui, volta para o estado escolheAcao se ainda tiver pontos.

		if (g.verificaPontosAcao() > 0)
			g.voltaEscolheAcao();
		else 
			g.voltaSemPontos();
	

	}

	private void getUserTunelPositionChoice () {
		int opcao=-1;

		while (opcao < 0 || opcao > 4) {
			System.out.println();
			System.out.println("Está na posicao " + g.getCardsiege().getTunel() + " do tunel");
			System.out.println("Para que posicao do tunel deseja ir? ");
			System.out.println("1-Castelo");
			System.out.println("2-Tunel 1");
			System.out.println("3-Tunel 2");
			System.out.println("4-Enemy Lines");
			System.out.println("0-Voltar atras");

			try {
				while(!s.hasNextInt()) s.next(); opcao = s.nextInt();
			} catch (InputMismatchException naoescreveuinteiro){
				System.out.println("[ERRO] Valor Inválido\n");
				s.nextLine();
				for(int i = 0 ; i < 2; i++) System.out.println();
			}

			if (opcao == 0) {
				g.voltaEscolheAcao();
				System.out.println("\n\n\n\n\n");
				return;
			}
                        g.movimentoTunel(opcao - 1);//verificacao
				System.out.println("Nova posicao do tunel = " + g.getCardsiege().getTunel());
				System.out.println("Pontos Disponiveis = " + g.verificaPontosAcao());
				//g.voltaEscolheAcao();
				return;

		}
	}

	private void getUserCompraAP() {
		int opcao=-1;

		while (opcao < 1 || opcao > 3) {
			System.out.println();
			System.out.println("Deseja comprar um Ponto de Ação em troca \nde... ");
			System.out.println("1-moral.");
			System.out.println("2-provisões.");
			System.out.println("3-Não, prosseguir com o jogo.");
			System.out.println(" -> ");

			try {
				while(!s.hasNextInt()) s.next(); opcao = s.nextInt();
			} catch (InputMismatchException naoescreveuinteiro){
				System.out.println("[ERRO] Valor Inválido\n");
				s.nextLine();
				for(int i = 0 ; i < 2; i++) System.out.println();
			}

			if (opcao == 1) {
                            g.compraAPporMoral();//isto esta certo 
                            return;
                        }
			if (opcao == 2){
                            g.compraAPporProvisoes();
                            return;
                        }
			if (opcao == 3) {
				g.naoComprouAP();//lancamento de carta?
				return;
			}
			System.out.println("Não pode realizar o pretendido!\n");
			opcao = -1;
		}
			
	}

	private void funcaoDoGameOver() {
		// perdeu o jogo, iniciar um novo?
		int opcao=-1;

		while (opcao < 1 || opcao > 2) {
			System.out.println();
			System.out.println("Perdeu o jogo! ");
			System.out.println("1-Iniciar um novo jogo");
			System.out.println("2-Terminar programa");
			System.out.println(" -> ");

			try {
				while(!s.hasNextInt()) s.next(); opcao = s.nextInt();
			} catch (InputMismatchException naoescreveuinteiro){
				System.out.println("[ERRO] Valor Inválido\n");
				s.nextLine();
				for(int i = 0 ; i < 2; i++) System.out.println();
			}

			if (opcao == 1)
				g.iniciarNovoJogo();//cheatolho
			if(opcao == 2)
				g.terminarPrograma();
			
		}

	}

	public void run(){

		while ( !(g.getState() instanceof terminarPrograma) ) {

			if(g.getState() instanceof esperaPorCarta){
				System.out.println(g.getState().toString());
				getUserInputWaitForCard();
			} 
			if(g.getState() instanceof escolheAcao) {
				if (g.verificaPontosAcao() == 0) 
					g.ficouSemPontos();
				else {
					System.out.println(g.getState().toString());
					getUserInputAction();
				}
			} 
			if(g.getState() instanceof escolhaLinhadoInimigo) {
				if (g.verificaPontosAcao() == 0) 
					g.voltaSemPontos();
				else {
					System.out.println(g.getState().toString());
					getUserEnemyLineChoice();
				}
			}
			if(g.getState() instanceof escolhePosicaoTunel) {
				System.out.println(g.getState().toString());
				getUserTunelPositionChoice();
			}
			
			if(g.getState() instanceof esperaCompraAP) {
				System.out.println(g.getState().toString());
				getUserCompraAP();
			}

			if(g.getState() instanceof gameOver) {
				System.out.println(g.getState().toString());
				funcaoDoGameOver();
			}
		
		}
	}
}
