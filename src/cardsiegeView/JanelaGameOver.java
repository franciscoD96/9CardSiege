package cardsiegeView;


import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import logicaJogo.ObservableGame;
import model.states.*;


public class JanelaGameOver  extends JFrame implements Observer{

	ObservableGame obsGame;
	
	JFrame frame;
	JButton novoJogo;
	JButton acabarJogo;
	//btn.setBackground(Color.BLACK);
	
	public JanelaGameOver(ObservableGame obs) {
		super("GameOver");
		obsGame = obs;
		obsGame.addObserver(this);

		setVisible(false);
        this.setSize(300, 100);
        this.setMinimumSize(new Dimension(300, 100));
        this.setMaximumSize(new Dimension(300, 100));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
		        
		setupBotoes();
		
		JLabel texto = new JLabel("Deseja Começar um novo Jogo ou Sair?");
		
		add(texto);
		JPanel linha = new JPanel();
		linha.add(novoJogo);
		linha.add(acabarJogo);
		add(linha);
		
		
	}
	
	private void setupBotoes() {
		frame = new JFrame("Game Over");
		novoJogo = new JButton("Novo Jogo");
		acabarJogo = new JButton("Sair");

		
		novoJogo.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			
			obsGame.iniciarNovoJogo();
			setVisible(false);
	     }});
		
		acabarJogo.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			System.exit(0);
	     }});
		

	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(obsGame.get().getState() instanceof gameOver)
			setVisible(true);


		
	}

}