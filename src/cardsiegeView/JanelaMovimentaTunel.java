package cardsiegeView;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logicaJogo.ObservableGame;
import model.states.*;

@SuppressWarnings("serial")
public class JanelaMovimentaTunel extends JFrame implements Observer{

	ObservableGame obsGame;
	
	JFrame frame;
	JButton entrada;
	JButton tunel1;
	JButton tunel2;
	JButton linhaInimigo;
	
	public JanelaMovimentaTunel(ObservableGame obs) {
		super("Movimento Tunel");
		obsGame = obs;
		obsGame.addObserver(this);
		
		setVisible(false);
		setAlwaysOnTop(true);
		
        this.setSize(400, 100);
        this.setMinimumSize(new Dimension(400, 100));
        this.setMaximumSize(new Dimension(400, 100));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // voltar oa estado anterior.
        validate();
		
		setupBotoes();
		
		JLabel texto = new JLabel("Que posição deseja dentro do tunel?");
		
		add(texto);
		JPanel linha = new JPanel();
		linha.add(entrada);
		linha.add(tunel1);
		linha.add(tunel2);
		linha.add(linhaInimigo);
		add(linha);
	}
	
	private void setupBotoes() {
		frame = new JFrame("Escolha do Ataque");
		entrada = new JButton("Entrada");
		tunel1 = new JButton("Tunel 1");
		tunel2 = new JButton("Tunel 2");
		linhaInimigo = new JButton("Linha do Inimigo");

		
		entrada.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			
			obsGame.movimentoTunel(0);
			setVisible(false);
	     }});
		tunel1.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			obsGame.movimentoTunel(1);
			setVisible(false);
			
	     }});
		tunel2.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			obsGame.movimentoTunel(2);
			setVisible(false);
			
	     }});
		linhaInimigo.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			obsGame.movimentoTunel(3);
			setVisible(false);
			
	     }});
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(obsGame.get().getState() instanceof escolhePosicaoTunel)
			setVisible(true);


		
	}

}
