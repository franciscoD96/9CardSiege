package model.states;

import logicaJogo.cardsiege;

public class esperaCompraAP extends StateAdapter{
	
	public esperaCompraAP (cardsiege dataCardsiege) {
		super(dataCardsiege);
	}
	
	public IStates comprouAP() {
		return new escolheAcao(getDataCardsiege());
	}
        
        public IStates compraAPporMoral(){
            
            if (getDataCardsiege().getMoral() > 0) {
                    getDataCardsiege().setMoral(getDataCardsiege().getMoral() - 1);
                    getDataCardsiege().setPontosDisponiveis(getDataCardsiege().getPontosDisponiveis() + 1);
                    
                    return new escolheAcao(getDataCardsiege()); //true
            } else 
                
                return new esperaCompraAP(getDataCardsiege());//false      
            
        }
        
        public IStates compraAPporProvisoes(){
            if (getDataCardsiege().getProvisoes() > 0) {
    		getDataCardsiege().setProvisoes(getDataCardsiege().getProvisoes() - 1);
    		getDataCardsiege().setPontosDisponiveis(getDataCardsiege().getPontosDisponiveis() + 1);
                
    		return new escolheAcao(getDataCardsiege());//true
            } else 
                
                return new esperaCompraAP(getDataCardsiege());//false
        }
	
	public IStates naoComprouAP() {
		return new esperaPorCarta(getDataCardsiege());
	}
	
	public IStates voltaEscolheAcao() {
		return new escolheAcao(getDataCardsiege());
	}
	
	public IStates acabouComOJogo() {
		return new gameOver(getDataCardsiege());
	}
	
}
