
package model.states;

import logicaJogo.cardsiege;


public class escolhePosicaoTunel extends StateAdapter{
	
	public escolhePosicaoTunel (cardsiege dataCardsiege) {
		super(dataCardsiege);
	}
	
        @Override
        public IStates movimentoTunel(int posicaoEscolhida) {
            
            if (getDataCardsiege().getOnlyRaidSabotage() == false)
            	getDataCardsiege().movimentoTunel(posicaoEscolhida);
            return new escolheAcao(getDataCardsiege());   
                //return new escolhePosicaoTunel(getDataCardsiege());  
        }
	
        @Override
	public IStates voltaEscolheAcao() {
		return new escolheAcao(getDataCardsiege());
	}
}