package model.states;

import logicaJogo.cardsiege;

public class gameOver extends StateAdapter{
	public gameOver(cardsiege dataCardsiege)
    {
        super(dataCardsiege);
    }
	public IStates sairDoJogo() {
		return new terminarPrograma(getDataCardsiege());
	}
	public IStates iniciarNovoJogo() {
                //new game deveria estar aqui..
		return new esperaPorCarta(getDataCardsiege());
	}
}
