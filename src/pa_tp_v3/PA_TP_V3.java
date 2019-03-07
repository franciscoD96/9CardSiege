/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa_tp_v3;

import cardsiegeView.cardsiegeView;
import logicaJogo.ModelGame;
import logicaJogo.ObservableGame;
import ui.text.TextUserInterface;

public class PA_TP_V3 {

    public static void main(String[] args) {
        TextUserInterface ui = new TextUserInterface(new ModelGame());
        ui.run();
    }

}