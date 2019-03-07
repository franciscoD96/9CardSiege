package cardsiegeView;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import logicaJogo.ModelGame;
import logicaJogo.ObservableGame;
import model.states.*;
import ui.text.GuardaJogo;

public class cardsiegeView extends JFrame implements Observer{
	private static final long serialVersionUID = 5519813641205063999L;
	
	ObservableGame obsGame;
	//cardsiegePanel painelJogo;

	PainelCartas pCartas;
	PainelBotoes pBotoes;
	PainelDaDireita pDireita;
	
	public cardsiegeView(ObservableGame obsgame) {
		super("9 Card Siege");

		obsGame = obsgame;
		obsGame.addObserver(this);

		pCartas = new PainelCartas(obsGame);
		pBotoes = new PainelBotoes(obsGame);
		pDireita = new PainelDaDireita(obsGame);
		
		
		adicionarComponentes();
		adicionarMenu();
		setVisible(true);
        this.setSize(690, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);  
        validate();
        update(obsGame,null);//para ficar bloqueado
	}
	

	public void adicionarComponentes() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pDireita, "East");
        cp.add(pCartas, "North"); 
        cp.add(pBotoes);
        
        
	}

	private void adicionarMenu() {
		JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        
        JMenuItem readObjJMI = new JMenuItem("Load");
        readObjJMI.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        
        JMenuItem saveObjJMI = new JMenuItem("Save");
        saveObjJMI.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        
        JMenuItem exitJMI = new JMenuItem("Exit");
        exitJMI.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        gameMenu.add(readObjJMI);
        gameMenu.add(saveObjJMI);
        gameMenu.addSeparator();    
        gameMenu.add(exitJMI);
        menuBar.add(gameMenu);
        
        //mostrar dialog Box
        readObjJMI.addActionListener(new LoadObjMenuBarListener());
        saveObjJMI.addActionListener(new SaveObjMenuBarListener());
        exitJMI.addActionListener(new ExitListener());
     
	}
	
	
	public void update(Observable arg0, Object arg1) {
		if(obsGame.get().getState() instanceof esperaPorCarta) {
			setEnabled(false);
		}
		if(obsGame.get().getState() instanceof escolheAcao) {
			setEnabled(true);
		}
		if(obsGame.get().getState() instanceof escolhaLinhadoInimigo) {
			setEnabled(false);
		}
		if(obsGame.get().getState() instanceof escolhePosicaoTunel) {
			setEnabled(false);
		}
		if(obsGame.get().getState() instanceof gameOver) {
			setEnabled(false);
		
		}
		if(obsGame.get().getState() instanceof esperaCompraAP) {
			setEnabled(false);
		}
		if(obsGame.get().getState() instanceof escolheRallyTroops) {
			setEnabled(false);
		}
		System.out.println(obsGame.get().getState());
		repaint();
	}
	
	
	class NewObjMenuBarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
             obsGame.quit();//setGameModel(new GameModel());
        }
    }
    
   
	class SaveObjMenuBarListener implements ActionListener {
		
		 @Override
	        public void actionPerformed(ActionEvent e) {
			GuardaJogo.guardarJogo(obsGame.get());
			 	System.out.println("Guardei o jogo");
		 }
	}
	
	class LoadObjMenuBarListener implements ActionListener {
		
		 @Override
	        public void actionPerformed(ActionEvent e) {
			 obsGame.setGameModel(GuardaJogo.carregarJogo());
			 	System.out.println("Carreguei o jogo");
		 }
	}

    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}
