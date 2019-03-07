package model.states;

import java.io.Serializable;
import logicaJogo.cardsiege;

public class escolheAcao extends StateAdapter implements Serializable{

	public escolheAcao (cardsiege dataCardsiege) {
		super(dataCardsiege);
	}

	public IStates escolhaLinhadoInimigo() {
		return new escolhaLinhadoInimigo(getDataCardsiege());
	}

	public IStates repararMuralha(){
		if (getDataCardsiege().getPontosDisponiveis() > 0)
			if (getDataCardsiege().getIntegridade() < 4 
					&& getDataCardsiege().getOnlyRaidSabotage() == false) {
				getDataCardsiege().repararMuralha();
				getDataCardsiege().setPontosDisponiveis(getDataCardsiege().getPontosDisponiveis() - 1);
	
				return new escolheAcao(getDataCardsiege());//true
			}
		return new escolheAcao(getDataCardsiege());//false.. nao e especificado de nenhum maneira
	}

	public IStates discursoMotivacional(){

//		if (getDataCardsiege().getPontosDisponiveis() > 0)
//			if (getDataCardsiege().getMoral() < 4 
//				&& getDataCardsiege().getOnlyRaidSabotage() == false) {
//			getDataCardsiege().discursoMotivacional();
//			getDataCardsiege().setPontosDisponiveis(getDataCardsiege().getPontosDisponiveis() - 1);
//			return new escolheAcao(getDataCardsiege());//true
//		}
//		return new escolheAcao(getDataCardsiege());//falso
		return new escolheRallyTroops(getDataCardsiege());
	}

	public IStates menosProvisoesPorMaisMoral(){
		getDataCardsiege().setProvisoes(getDataCardsiege().getProvisoes()- 1);
		getDataCardsiege().setMoral(getDataCardsiege().getMoral() + 1);
		return new escolheAcao(getDataCardsiege());//devolve para o estado normal
	}

	public IStates escolhePosicaoTunel() {
		return new escolhePosicaoTunel(getDataCardsiege());
	}

	public IStates passarTurno() {// voltar para trás
		return new esperaPorCarta(getDataCardsiege());
	}

	public IStates roubarMantimentos(){
		if (getDataCardsiege().getTunel() != 3) {
			return new escolheAcao(getDataCardsiege());//falso
		} else {
			if (getDataCardsiege().getPontosDisponiveis() > 0)
				getDataCardsiege().roubarMantimentos();
				
			return new escolheAcao(getDataCardsiege());//true
		}
	}
	public IStates sabotarInimigo () {
		if (getDataCardsiege().getTunel() != 3) {
			return new escolheAcao(getDataCardsiege());//falso
		} else {
			if (getDataCardsiege().getPontosDisponiveis() > 0)
				getDataCardsiege().sabotarInimigo();
		
			return new escolheAcao(getDataCardsiege());//true
		}
	}

	public IStates ficouSemPontos() {
		return new esperaCompraAP(getDataCardsiege());
	}

	public IStates perdeuOJogo() {
		return new gameOver(getDataCardsiege());
	}

}
