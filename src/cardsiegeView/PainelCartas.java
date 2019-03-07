package cardsiegeView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import logicaJogo.ObservableGame;
import model.states.escolhaLinhadoInimigo;
import model.states.escolheAcao;
import model.states.escolhePosicaoTunel;
import cardsiegeView.Resource;

@SuppressWarnings("serial")
public class PainelCartas extends JPanel implements Observer{

	ObservableGame obsGame;

	static final String imageFiles[]={"/imagens/castelo.PNG","/imagens/inimigo.PNG","/imagens/carta1.PNG","/imagens/carta2.PNG"
			,"/imagens/carta3.PNG","/imagens/carta4.PNG","/imagens/carta5.PNG","/imagens/carta6.PNG","/imagens/carta7.PNG","/imagens/9cardsiege.PNG"};
	static Image cartaImagem[]=new Image[imageFiles.length];

	public PainelCartas (ObservableGame obsGame) {
		this.obsGame = obsGame;

		buscaImagens(obsGame);
		setPreferredSize(new Dimension(this.getWidth(), 316));


	}

	static void buscaImagens(ObservableGame game){
		int i=0;
		for(String fileName:imageFiles){
			try {
				cartaImagem[i++]=ImageIO.read(Resource.getResourceFile(fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro na imagem");
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void paint(Graphics g)
	{
		int larguraImagem = cartaImagem[0].getWidth(this);
		g.drawImage(cartaImagem[0],0,0,this);
		g.drawImage(cartaImagem[1], larguraImagem, 0, this);

		int[] estadosLinhas = obsGame.get().getEstadoLinhas();		// obter o numero da carta

		g.setColor(Color.green);
		
		if(obsGame.get().getState() instanceof escolheAcao ||
			obsGame.get().getState() instanceof escolhaLinhadoInimigo||
			obsGame.get().getState() instanceof escolhePosicaoTunel) {
			g.drawImage(cartaImagem[estadosLinhas[10] + 1], (larguraImagem * 2), 0, this); // getEventoAtual
			if (estadosLinhas[9] < 4)	g.drawOval(460, 8 + 100*estadosLinhas[9], 210, 100);
		} else
			g.drawImage(cartaImagem[9], (larguraImagem * 2), 0, this); // getEventoAtual
		

		
		if (estadosLinhas[0] > 0 && estadosLinhas[0] <= 4)		g.fillOval(15, (220 - 52 * estadosLinhas[0]),32,32);
		else if (estadosLinhas[0] == 0)							g.fillOval(77,220, 32, 32);
		if (estadosLinhas[1] > 0 && estadosLinhas[1] <= 4)		g.fillOval(87, (220 - 52 * estadosLinhas[1]),32,32);
		else if (estadosLinhas[1] == 0)							g.fillOval(87,220, 32, 32);
		if (estadosLinhas[2] > 0 && estadosLinhas[2] <= 4)		g.fillOval(160, (220 - 52 * estadosLinhas[2]),32,32);
		else if (estadosLinhas[2] == 0) 						g.fillOval(97,220, 32, 32);
		
		g.fillOval(8 + 35 * estadosLinhas[3], 275,30,30);
		
		if (estadosLinhas[4] == 1) 		g.fillOval(165, 274, 32, 32);
		if (estadosLinhas[4] == 2)	   {g.fillOval(165, 274, 32, 32);
										g.fillOval(165, 236, 32, 32);}

		if (estadosLinhas[5] > 0 && estadosLinhas[5] <= 4)		g.fillOval(244, 4 + 52 * estadosLinhas[5], 32, 32);
		else if(estadosLinhas[5] == 0)							g.fillOval(311, 4, 32, 32);
		if (estadosLinhas[6] > 0 && estadosLinhas[6] <= 4)		g.fillOval(321, 4 + 52 * estadosLinhas[6], 32, 32);
		else if(estadosLinhas[6] == 0)							g.fillOval(321, 4, 32, 32);
		if (estadosLinhas[7] > 0 && estadosLinhas[7] <= 4) 		g.fillOval(397, 4 + 52 * estadosLinhas[7], 32, 32);
		else if(estadosLinhas[7] == 0)							g.fillOval(331, 4, 32, 32);
		
		g.fillOval(166 + 77*estadosLinhas[8], 273, 32, 32);
		
}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
