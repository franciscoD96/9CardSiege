package cardsiegeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import logicaJogo.ObservableGame;

@SuppressWarnings("serial")
public class PainelDaDireita  extends JPanel implements Observer {
	ObservableGame obsGame;
	JTextArea texto;
	static final String imageFiles[]={"/imgsDado/Alea_1.png","/imgsDado/Alea_2.png","/imgsDado/Alea_3.png",
			"/imgsDado/Alea_4.png","/imgsDado/Alea_5.png","/imgsDado/Alea_6.png"};
	static Image dadoImagem[]=new Image[imageFiles.length];
	
	PainelDaDireita(ObservableGame obsGame){
		this.obsGame = obsGame;
		
		buscaImagens(obsGame);
		
		texto = new JTextArea();
		texto.setWrapStyleWord(true);
		texto.setLineWrap(false);
		texto.setEditable(false);
		
		//texto.setLocation(new Point(dadoImagem[0].getHeight(this) - 20, 0));
	    texto.setForeground(Color.RED);
		texto.setBackground(Color.LIGHT_GRAY);
		add(texto);	
		
		
	}
	

	static void buscaImagens(ObservableGame game){
		int i=0;
		for(String fileName:imageFiles){
			try {
				dadoImagem[i++]=ImageIO.read(Resource.getResourceFile(fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro na imagem do dado");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	}

	public void paint(Graphics g) {
		int ultLancamento = obsGame.get().getUltimoLancamento(); 
		switch(ultLancamento) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			System.out.println(ultLancamento);
			g.drawImage(dadoImagem[ultLancamento - 1],0,this.getHeight() - dadoImagem[0].getHeight(this), this);
			break;
		default: 
			break;
		}
		texto.setText(
				"Dia atual: " + String.valueOf(obsGame.get().getCardsiege().getDiaAtual() + 1) + "\n" +
				"Evento atual: " + String.valueOf(obsGame.get().getCardsiege().getEventoAtual() + 1)  + "\n" +
				"Pontos Disponíveis: " + String.valueOf(obsGame.get().verificaPontosAcao()) );
		//texto.paint(g);
	}
}
