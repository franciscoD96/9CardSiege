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

public class JanelaEscolhaInimigo extends JFrame implements Observer {

	ObservableGame obsGame;
	
	JFrame frame;
	JButton escada;
	JButton ariete;
	JButton torre;
	
	public JanelaEscolhaInimigo(ObservableGame obs) {
		super("Escolha");
		obsGame = obs;
		obsGame.addObserver(this);

		
		setVisible(false);
		
        this.setSize(300, 100);
        this.setMinimumSize(new Dimension(300, 100));
        this.setMaximumSize(new Dimension(300, 100));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// voltar oa estado anterior.
        validate();
		
		setupBotoes();
		
		JLabel texto = new JLabel("Que inimigo deseja atacar?");
		
		add(texto);
		JPanel linha = new JPanel();
		linha.add(escada);
		linha.add(ariete);
		linha.add(torre);
		add(linha);
	}
	
	private void setupBotoes() {
		frame = new JFrame("Escolha do Ataque");
		escada = new JButton("Escada");
		ariete = new JButton("Aríete");
		torre = new JButton("Torre");
		
		escada.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			
			obsGame.realizaAtaque(1);
			setVisible(false);
	     }});
		ariete.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			obsGame.realizaAtaque(2);
			setVisible(false);
			
	     }});
		torre.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			obsGame.realizaAtaque(3);
			setVisible(false);
			
	     }});
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(obsGame.get().getState() instanceof escolhaLinhadoInimigo)
			setVisible(true);


		
	}

}
