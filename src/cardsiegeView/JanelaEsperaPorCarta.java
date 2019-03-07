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

public class JanelaEsperaPorCarta extends JFrame implements Observer{

	ObservableGame obsGame;
	
	JFrame frame;
	JButton lancarCarta;
	JLabel texto;

	
	public JanelaEsperaPorCarta(ObservableGame obs) {
		super("Lançar Carta");
		obsGame = obs;
		obsGame.addObserver(this);

		
		setVisible(true);//começa a true
		setAlwaysOnTop(true);
		
        this.setSize(300, 100);
        this.setMinimumSize(new Dimension(300, 100));
        this.setMaximumSize(new Dimension(300, 100));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // voltar oa estado anterior.
        validate();
		        
		setupBotoes();
		
		JLabel texto = new JLabel();//"Prima o Botão para Lançar a Carta");
		
		add(texto);
		JPanel linha = new JPanel();
		linha.add(lancarCarta);
		add(linha);
		
		
	}
	
	private void setupBotoes() {
		frame = new JFrame("Escolha do Ataque");
		lancarCarta = new JButton("Lançar Carta");

		
		lancarCarta.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			
			obsGame.lancamentoCarta();
			setVisible(false);
	     }});

		
		lancarCarta.registerKeyboardAction(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				obsGame.lancamentoCarta();
				setVisible(false);
		    }
		
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), JComponent.WHEN_FOCUSED);

	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(obsGame.get().getState() instanceof esperaPorCarta)
			setVisible(true);


		
	}

}