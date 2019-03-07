package cardsiegeView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logicaJogo.ObservableGame;
import model.states.esperaCompraAP;

@SuppressWarnings("serial")
public class JanelaCompraAP extends JFrame implements Observer{

	ObservableGame obsGame;
	
	JButton porMoral;
	JButton porProvisoes;
	JButton nao;
	
	

	public JanelaCompraAP(ObservableGame obs) {
		super("Comprar Pontos de Ação");
		obsGame = obs;
		obsGame.addObserver(this);
		
		setVisible(false);
		setAlwaysOnTop(true);
		
        this.setSize(400, 100);
        this.setMinimumSize(new Dimension(400, 100));
        this.setMaximumSize(new Dimension(400, 100));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
     
        setupBotoes();
        
        JPanel linha = new JPanel();
		linha.add(porMoral);
		linha.add(porProvisoes);
		linha.add(nao);
		add(linha);
	}
	
	void setupBotoes() {
		porMoral = new JButton("por Moral");
		porProvisoes = new JButton("por Provisões");
		nao = new JButton("Não, vou continuar");
		
		porMoral.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev) {
			obsGame.compraAPporMoral();
			setVisible(false);
	     }});
		porProvisoes.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			obsGame.compraAPporProvisoes();
			setVisible(false);
	     }});
		nao.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent ev){
			//obsGame.naoComprouAP();
			obsGame.voltaEscolheAcao();
			setVisible(false);
	     }});
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(obsGame.get().getState() instanceof esperaCompraAP) {
			setVisible(true);
		}
	}
	

}
