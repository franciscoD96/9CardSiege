package cardsiegeView;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.sun.javafx.tk.Toolkit;

import logicaJogo.ObservableGame;
import cardsiegeView.cardsiegeView;

@SuppressWarnings("serial")
public class PainelBotoes extends JPanel implements Observer {
	
	JButton ataqueAgua;
	JButton ataqueArqueiros;
	JButton ataqueCorpo;
	JButton coupure;
	JButton rally;
	JButton roubar;
	JButton sabotar;
	JButton movimentarTropas;
	JButton passarTurno;
	JButton comprarPontos;
	
	ObservableGame obsGame;
	JanelaEscolhaInimigo janelaEscolhaInimigo;
	JanelaMovimentaTunel janelaMovimentaTunel;
	JanelaEsperaPorCarta janelaEsperaPorCarta;
	JanelaCompraAP janelaCompraAP;
	JanelaGameOver janelaGameOver;
	JanelaEscolheRallyTroops janelaEscolheRallyTroops;
	
	PainelBotoes(ObservableGame obsGame){
		this.obsGame = obsGame;
		int altura=500,largura=700;
		
		setUpBotoes();
		
		setBackground(Color.LIGHT_GRAY);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		JLabel texto = new JLabel("Realizar um ataque");
		texto.setHorizontalAlignment(JLabel.LEFT);
		add(texto);	
		janelaEscolhaInimigo = new JanelaEscolhaInimigo(obsGame);
		janelaMovimentaTunel = new JanelaMovimentaTunel(obsGame);
		janelaEsperaPorCarta = new JanelaEsperaPorCarta(obsGame);
		janelaCompraAP = new JanelaCompraAP(obsGame);
		janelaGameOver = new JanelaGameOver(obsGame);
		janelaEscolheRallyTroops = new JanelaEscolheRallyTroops(obsGame);
		
		
		//define as localizacoes das janelas
		//altura e largura da janela principal
		
		//janelaEscolhaInimigo.setLocation((largura-300)/2,(altura-100)/2);
		//janelaMovimentaTunel.setLocation((largura-400)/2,(altura-100)/2);
		//janelaEsperaPorCarta.setLocation((largura-300)/2,(altura-100)/2);
		janelaEscolhaInimigo.setLocationRelativeTo(null);
		janelaMovimentaTunel.setLocationRelativeTo(null);
		janelaEsperaPorCarta.setLocationRelativeTo(null);
		janelaCompraAP.setLocationRelativeTo(null);
		janelaGameOver.setLocationRelativeTo(null);
		janelaEscolheRallyTroops.setLocationRelativeTo(null);

		
		JPanel linha1 = new JPanel();
		linha1.setBackground(Color.LIGHT_GRAY);
		linha1.add(ataqueArqueiros);
		linha1.add(ataqueAgua);
		linha1.add(ataqueCorpo);
		add(linha1);
		
		texto = new JLabel("Melhorar");
		add(texto);
		
		JPanel linha2 = new JPanel();
		linha2.setBackground(Color.LIGHT_GRAY);
		linha2.add(coupure);
		linha2.add(rally);
		add(linha2);
		
		texto = new JLabel("Mandar as tropas no tunel");
		add(texto);
		
		JPanel linha3 = new JPanel();
		linha3.setBackground(Color.LIGHT_GRAY);
		linha3.add(roubar);
		linha3.add(sabotar);
		linha3.add(movimentarTropas);
		add(linha3);
		
		JPanel linha4 = new JPanel();
		linha4.setBackground(Color.LIGHT_GRAY);
		linha4.add(passarTurno);
		linha4.add(comprarPontos);
		add(linha4);
		
	}
	
	private void setUpBotoes() {
		
		//ataques
		ataqueArqueiros = new JButton("Arqueiros");
		ataqueArqueiros.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			
			obsGame.escolhaLinhadoInimigo(1); 
			janelaEscolhaInimigo.setVisible(true);
			
			
		   }});
		
		ataqueAgua = new JButton("Água Quente");
		ataqueAgua.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			
			obsGame.escolhaLinhadoInimigo(2); 
			janelaEscolhaInimigo.setVisible(true);
			
	     }});

		ataqueCorpo = new JButton("Corpo a corpo");
		ataqueCorpo.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			
				//if(obsGame.get().getCardsiege().quantosEstaoProximos() == 1) {
				//	obsGame.realizaAtaque(obsGame.get().getCardsiege().quemEstaProximo()); 
				//}else 
			if(obsGame.get().getCardsiege().quantosEstaoProximos() > 0) {
					obsGame.escolhaLinhadoInimigo(3); 
					janelaEscolhaInimigo.setVisible(true);
				}
			
	     
			}});
		
		//melhora x
		coupure = new JButton("Melhorar Muralha");
		coupure.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			obsGame.repararMuralha();  
			
	     }});
		
		rally = new JButton("Discurso Motivacional");
		rally.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			obsGame.discursoMotivacional();
	     }});
		
		roubar = new JButton("Roubar");
		roubar.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			obsGame.roubarMantimentos();
	     }});
		sabotar = new JButton("Sabotar o inimigo");
		sabotar.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			obsGame.sabotarInimigo();
	     }});
		movimentarTropas = new JButton("Movimentar");
		movimentarTropas.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			obsGame.escolhePosicaoTunel(); 
			janelaMovimentaTunel.setVisible(true);
	     }});
		
		passarTurno = new JButton("Passar o Turno");
		passarTurno.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			obsGame.passarTurno();
	     }});
		comprarPontos = new JButton("Comprar Pontos");
		comprarPontos.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			obsGame.ficouSemPontos();
			janelaCompraAP.setVisible(true);
	     }});
		
		
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
		
	}

}
