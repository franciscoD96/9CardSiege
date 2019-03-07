package model.states;

import logicaJogo.cardsiege;

public class escolheRallyTroops extends StateAdapter{
	
public escolheRallyTroops (cardsiege dataCardsiege) {
	super(dataCardsiege);
}

public IStates escolheRallyTroops() {
	return new escolheRallyTroops(getDataCardsiege());
}

@Override
public IStates fazDiscurso() {
		if (getDataCardsiege().getPontosDisponiveis() > 0)
			if (getDataCardsiege().getMoral() < 4 
				&& getDataCardsiege().getOnlyRaidSabotage() == false) {
				getDataCardsiege().discursoMotivacional();
				getDataCardsiege().setPontosDisponiveis(getDataCardsiege().getPontosDisponiveis() - 1);
				return new escolheAcao(getDataCardsiege());//true
	}
		return new escolheAcao(getDataCardsiege());//falso
}

	@Override
public IStates ofereceSupplies() {
	if(getDataCardsiege().adicionaModMoral())
		return new escolheRallyTroops(getDataCardsiege());
	else
		return new escolheRallyTroops(getDataCardsiege());
	
}
}