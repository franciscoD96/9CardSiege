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

public class JanelaEscolheRallyTroops extends JFrame implements Observer{

	ObservableGame obsGame;
	
	JFrame frame;
	JButton fazDiscurso;
	JButton ofereceSupplies;

	
	public JanelaEscolheRallyTroops(ObservableGame obs) {
		super("Discurso Motivacional");
		obsGame = obs;
		obsGame.addObserver(this);

		
		setVisible(false);
		
        this.setSize(300, 100);
        this.setMinimumSize(new Dimension(300, 100));
        this.setMaximumSize(new Dimension(300, 100));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // voltar oa estado anterior.
        validate();
		        
		setupBotoes();
		
		JLabel texto = new JLabel("Faça o discurso motivacional ou de Provisoes");
		
		add(texto);
		JPanel linha = new JPanel();
		linha.add(fazDiscurso);
		linha.add(ofereceSupplies);
		add(linha);
		
		
	}
	
	private void setupBotoes() {
		frame = new JFrame("Fazer Discurso Motivacional");
		fazDiscurso = new JButton("Faz Discurso");
		ofereceSupplies = new JButton("Oferece Supplies");

		
		fazDiscurso.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			
			obsGame.fazDiscurso();
			setVisible(false);
	     }});
		
		ofereceSupplies.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			
			obsGame.ofereceSupplies();
	     }});
				

	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(obsGame.get().getState() instanceof escolheRallyTroops)
			setVisible(true);


		
	}

}