package model.states;

import logicaJogo.cardsiege;

public class esperaPorCarta extends StateAdapter{
    
    public esperaPorCarta(cardsiege dataCardsiege)
    {
        super(dataCardsiege);
    }
  
    public IStates lancamentoCarta() {
        getDataCardsiege().resetModificadores();
    	getDataCardsiege().ativaEvento();
        
    	if (getDataCardsiege().quantosEstaoProximos() == 2) {
    		getDataCardsiege().setEscolha(3);
    		return new escolhaLinhadoInimigo(getDataCardsiege());
    	}else
    		return new escolheAcao(getDataCardsiege());
    }
    
}
