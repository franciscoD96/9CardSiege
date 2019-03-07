package model.states;

import logicaJogo.cardsiege;

public class escolhaLinhadoInimigo extends StateAdapter{
	
	public escolhaLinhadoInimigo (cardsiege dataCardsiege) {
		super(dataCardsiege);
	}
	
	public IStates voltaEscolheAcao() {
		return new escolheAcao(getDataCardsiege());
	}

	public IStates voltaSemPontos() {
		return new esperaCompraAP(getDataCardsiege());
	}

	public IStates ataqueArqueiros(int linhaEscolhida) {
		if (getDataCardsiege().getPontosDisponiveis() > 0)
		if (getDataCardsiege().verificaAtaqueArqueiros(linhaEscolhida) == true
				&& getDataCardsiege().getOnlyRaidSabotage()==false)  {
			getDataCardsiege().ataqueArqueiros(linhaEscolhida);
			getDataCardsiege().setPontosDisponiveis(getDataCardsiege().getPontosDisponiveis() - 1);
			return new escolheAcao(getDataCardsiege());//verdadeiro
		} 
			return new escolheAcao(getDataCardsiege());//deu erro logo volta a ele mesmo
	}

	public IStates ataqueAguaQuente(int linhaEscolhida) { 
		if (getDataCardsiege().getPontosDisponiveis() > 0)           
		if (getDataCardsiege().isTriggerBoilingWater() == false && getDataCardsiege().getOnlyRaidSabotage() == false)
			if (getDataCardsiege().verificaAtaqueAguaQuente(linhaEscolhida) == true) {
				getDataCardsiege().ataqueAguaQuente(linhaEscolhida);							// falta o +1 DRM
				getDataCardsiege().setTriggerBoilingWater(true);
				getDataCardsiege().setPontosDisponiveis(getDataCardsiege().getPontosDisponiveis() - 1);
				return new escolheAcao(getDataCardsiege());//verdadeiro
			}
		return new escolheAcao(getDataCardsiege());//falso
	}

	public IStates ataqueCorpoACorpo(int linhaEscolhida) {
		if (getDataCardsiege().getPontosDisponiveis() > 0)
		if (getDataCardsiege().verificaAtaqueCorpoACorpo(linhaEscolhida) == true && getDataCardsiege().getOnlyRaidSabotage() == false) {
			getDataCardsiege().ataqueCorpoACorpo(linhaEscolhida);
			getDataCardsiege().setPontosDisponiveis(getDataCardsiege().getPontosDisponiveis() - 1);
			return new escolheAcao(getDataCardsiege());
		}
		return new escolheAcao(getDataCardsiege());//falso
	}

}