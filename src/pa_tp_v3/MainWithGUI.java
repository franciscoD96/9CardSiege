package pa_tp_v3;

import cardsiegeView.cardsiegeView;
import logicaJogo.ModelGame;
import logicaJogo.ObservableGame;
import ui.text.TextUserInterface;

public class MainWithGUI {

	public static void main (String[] args) {
		cardsiegeView GuI = new cardsiegeView(new ObservableGame());
	}
    
}