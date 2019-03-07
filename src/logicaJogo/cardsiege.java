package logicaJogo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class cardsiege implements Serializable {

	List<Carta> baralho;

	int diaAtual;
	int eventoAtual;
	int pontosDisponiveis = 0;

	int modtrebuchet = 0;
	int modescada = 0;
	int modariete = 0;
	int modtorre = 0;
	int modintegridade = 0;
	int modprovisoes = 0;
	int modCoupure = 0;
	int modSabotage = 0;
	int modMoral = 0;
	int modRaid = 0;
	boolean onlyRaidSabotage = false;
	int modCloseCombat = 0;
	int modCircleSpaces = 0;
	boolean triggerBoilingWater = false;


	int integridade = 4;
	int moral = 4;
	int provisoes = 4;
	int tunel = 0;
	int provisoesRoubadas = 0;

	int trebuchet = 3;
	int escada = 4;
	int ariete = 4; // battering ram
	int torre = 4;

	private int escolha;

	public cardsiege () {

		diaAtual = 0;
		eventoAtual = 0;

		baralho = new ArrayList<Carta>();
		

		baralho.add( new Carta(1, new Eventos[] {
				new Trebuchet (3, this),
				new Trebuchet (2, this),
				new Trebuchet (1, this)
		}));    
		baralho.add( new Carta(4, new Eventos[] {
				new DeathofaLeader (this),
				new GateFortified (this),
				new FlamingArrows (this)
		}));   
		baralho.add( new Carta(5, new Eventos[] {
				new VolleyofArrows (this),
				new Collapsed (this),
				new RepairedTrebuchet (this)
		}));  
		baralho.add( new Carta(6, new Eventos[] {
				new CoverofDarkness (this),
				new EnemyFatigue (this),
				new Rally (this)
		})); 
		baralho.add( new Carta(3, new Eventos[] {
				new SuppliesSpoiled (this),
				new BadWeather (this),
				new BoilingOil (this)
		}));
		baralho.add( new Carta(2, new Eventos[] {
				new Illness (this),
				new GuardsDistracted (this),
				new Trebuchet (1,this)
		})); 
		baralho.add( new Carta(7, new Eventos[] {
				new DeterminedEnemy (this),
				new IronShields(this),
				new Faith (this)
		})); 

		Collections.shuffle(baralho);

	}

	public boolean fimdeDia() { // retorna se o jogador terminou o jogo
		Collections.shuffle(baralho);
		diaAtual ++;
		eventoAtual = 0;
		
		if (provisoes > 0) provisoes --;
		
		if (tunel < 3) {
			provisoes += provisoesRoubadas;
			if (provisoes > 4) provisoes = 4;
			tunel = 0;
			provisoesRoubadas = 0;
		} else if( tunel == 3) {//bug pode haver provisoes existentes a retornar
                        provisoes += provisoesRoubadas;
			tunel = 0;
			provisoesRoubadas = 0;
			if (moral > 0) moral --;
		}
		
		if (diaAtual == 4) return false;
		else return true;
	}
	public void ativaEvento() {
		moral -= quantosEstaoProximos();
		pontosDisponiveis = baralho.get(eventoAtual).eventoDia(diaAtual);
	}

	public String toString() {
		return "    -------------------------------------------------------------------------------------------\n"
				+ "-:- Estado Atual do Jogo -:-\n" + 
				"\t_Inimigo_" + 
				"\nlinha da Escada: " + escada + 
				"\nlinha da Ariete: " + ariete + 
				"\nlinha da Torre: " + torre + 
				"\nTrebuchet: " + trebuchet + 
				"\n\t_Castelo_" +
				"\nlinha da Integridade: " + integridade + 
				"\nlinha da Moral: " + moral + 
				"\nlinha das Provisões: " + provisoes + 
				"\nTunel (pos): " + tunel + 
				"\nProvis. Roubadas: " + provisoesRoubadas + 
				"\n    -:-                     -:-";
	}
	public String imprimeEventoAtual() {
		return baralho.get(eventoAtual).imprimeEventoAtual(diaAtual);
	}


	public boolean validaLimites(int comparacao){
		boolean validacao = false;
		if(comparacao > 0 && comparacao <= 4)
			validacao = true;
		return validacao;
	}
	public boolean verificaAtaqueArqueiros(int linhaEscolhida) {
		if (linhaEscolhida == 1) {
			if (escada < 4 && escada >= 2) return true;
		} 
		if (linhaEscolhida == 2) {
			if (ariete < 4 && ariete >= 2) return true;
		} 
		if (linhaEscolhida == 3) {
			if (torre < 4 && torre >= 2) return true;
		} 
		return false;
	}
	public void ataqueArqueiros(int linhaEscolhida) {
                    //optimizar este codigo
		if (linhaEscolhida == 1) {
			int sorte = Dado.lancamento();
			if (sorte > 2 - modescada) escada ++;
		}
		if (linhaEscolhida == 2) {
			int sorte = Dado.lancamento();
			if (sorte > 3 - modariete) ariete ++;
		}
		if (linhaEscolhida == 3) {
			int sorte = Dado.lancamento();
			if (sorte > 4 - modtorre) torre ++;
		}
	}
	public boolean verificaAtaqueAguaQuente(int linhaEscolhida) {
		if (linhaEscolhida == 1) {
			if (escada == 1) return true;
		}
		if (linhaEscolhida == 2) {
			if (ariete == 1) return true;
		}
		if (linhaEscolhida == 3) {
			if (torre == 1)  return true;
		}
		return false;
	}
	public void ataqueAguaQuente(int linhaEscolhida) {	
            //otimizar
		if (linhaEscolhida == 1) {
			int sorte = Dado.lancamento();
			if (sorte > 2 - modescada) escada ++;
		}
		if (linhaEscolhida == 2) {
			int sorte = Dado.lancamento();
			if (sorte > 3 - modariete) ariete ++;
		}
		if (linhaEscolhida == 3) {
			int sorte = Dado.lancamento();
			if (sorte > 4 - modtorre) torre ++;
		}
		
		if (Dado.getUltimo() == 1)
			if (moral > 0) moral--;
		
	}
	public boolean verificaAtaqueCorpoACorpo (int linhaEscolhida) {
		if (linhaEscolhida == 1) {
			if (escada == 0) return true;
		} 
		if (linhaEscolhida == 2) {
			if (ariete == 0) return true;
		} 
		if (linhaEscolhida == 3) {
			if (torre == 0) return true;
		}
		return false;
	}
	public void ataqueCorpoACorpo (int linhaEscolhida) {
            //otimizar
		if (linhaEscolhida == 1) {
			int sorte = Dado.lancamento();
			if (sorte > 4 - modescada) escada ++;
		}
		if (linhaEscolhida == 2) {
			int sorte = Dado.lancamento();
			if (sorte > 4 - modariete) ariete ++;
		}
		if (linhaEscolhida == 3) {
			int sorte = Dado.lancamento();
			if (sorte > 4 - modtorre) torre ++;
		}

		if (Dado.getUltimo() == 1)
			if (moral > 0) moral--;
		
		
	}
	public void repararMuralha () {
		if (Dado.lancamento() >= 5 - modintegridade)
                    if(integridade < 4) integridade ++;
	}
	public void discursoMotivacional() {
		if (Dado.lancamento() >= 5 - modMoral)
			if (moral < 4) moral ++;
	}

	public void movimentoTunel(int posicaoEscolhida) {

		switch (tunel) {
		case 0:
			switch(posicaoEscolhida) {
			case 0:
				return;
			case 1: 
				if (pontosDisponiveis > 0) {
					setPontosDisponiveis(pontosDisponiveis - 1);
					tunel = 1;
				}
				break;
			case 2: 
				if (pontosDisponiveis > 0) {
					setPontosDisponiveis(pontosDisponiveis - 1);
					tunel = 2;
				}
				break;
			case 3: 
				if (pontosDisponiveis > 1) {
					setPontosDisponiveis(pontosDisponiveis - 2);
					tunel = 3;
				}
				break;
			default: break;
			}
			break;
		case 1: 
			switch(posicaoEscolhida) {
			case 0:
				if (pontosDisponiveis > 0) {
					setPontosDisponiveis(pontosDisponiveis - 1);
					tunel = 0;
				}
				break;
			case 1: 
				return;
			case 2: 
				tunel = 2;
				break;
			case 3: 
				if (pontosDisponiveis > 0) {
					setPontosDisponiveis(pontosDisponiveis - 1);
					tunel = 3;
				}
				break;
			default: break;
			}
			break;
		case 2: 
			switch(posicaoEscolhida) {
			case 0:
				if (pontosDisponiveis > 0) {
					setPontosDisponiveis(pontosDisponiveis - 1);
					tunel = 0;
				}
				break;
			case 1: 
				tunel = 1;
				break;
			case 2: 
				return;
			case 3: 
				if (pontosDisponiveis > 0) {
					setPontosDisponiveis(pontosDisponiveis - 1);
					tunel = 3;
				} 
				break;
			default: break;
			}
			break;
		case 3: 
			switch(posicaoEscolhida) {
			case 0:
				if (pontosDisponiveis > 1) {
					setPontosDisponiveis(pontosDisponiveis - 2);
					tunel = 0;
				} 
				break;
			case 1: 
				if (pontosDisponiveis > 0) {
					setPontosDisponiveis(pontosDisponiveis - 1);
					tunel = 1;
				}
				break;
			case 2: 
				if (pontosDisponiveis > 0) {
					setPontosDisponiveis(pontosDisponiveis - 1);
					tunel = 2;
				}
				break;
			case 3: 
				return;
			default: break;
			}
			break;
		}
		if (tunel == 0) {
			while (provisoes < 4) {
				if (provisoesRoubadas > 0) {
					provisoes ++;
					provisoesRoubadas --;
				}	else break;
			}
		}
	}

	public boolean adicionaModMoral() {
		if(provisoes > 1){
			this.provisoes -= 1;
			this.modMoral += 1;
			return true;
		}
		return false;
	}
	
	public boolean roubarMantimentos() {
		if (tunel == 3) {
			int lanc = Dado.lancamento();
			if (lanc >= 6 - modRaid)
				provisoesRoubadas = 2; // ficará sempre no máximo, 2.
			else if (lanc > 3 - modRaid) 
				if (provisoesRoubadas <= 1) // nnc mais que dois
					provisoesRoubadas += 1;
			else if(lanc == 1) {
				provisoesRoubadas = 0;
				tunel = 0;
				if (moral > 0) moral--;
			}
			pontosDisponiveis -=1;
			return true;
			//logs.adicionaLog("O utilizador escolheu roubarMantimentos, e ficou na posicao "+ tunel + " do tunel e com "+ provisoesRoubadas + " provisoes para levar para o castelo!");
		}
		return false;
	}
	public boolean sabotarInimigo() {
		if (tunel == 3) {
			int lanc = Dado.lancamento();
			if (lanc > 4 - modSabotage) {
				if (trebuchet > 2) // pode no mínimo ficar a 1
					trebuchet -= 1;
			} else 
			if (lanc == 1) {
				provisoesRoubadas = 0;
				tunel = 0;
				if (moral > 0) moral--;
			}
			pontosDisponiveis -=1;
			return true;
		}
		return false;
	}

	public int getUltimoLancamento () {
		return Dado.getUltimo();
	}

	public void resetModificadores() {
		modtrebuchet = 0;
		modescada = 0;
		modariete = 0;
		modtorre = 0;
		modintegridade = 0;
		modMoral = 0;
		modprovisoes = 0;
		modCoupure = 0;
		modSabotage = 0;
		modMoral = 0;
		modRaid = 0;
		onlyRaidSabotage = false;
		modCloseCombat = 0;
		modCircleSpaces = 0;
	}
	
	public void moveSlowestTroops(){  
		boolean []slowestTroops = {false, false, false};
		//1 - escada 2- ariete 3 - torre

		int highest = 0;

		//procura o mais baixo
		if( highest < getEscada() )
			highest = getEscada();
		if( highest < getAriete() )
			highest = getAriete();
		if( highest < getTorre() )
			highest = getTorre();

		//verifica se há outro na mesma posição
		if( highest == getEscada() )
			slowestTroops[0] = true;
		if( highest == getAriete() )
			slowestTroops[1] = true;
		if( highest == getTorre() )
			slowestTroops[2] = true;

		//efetua movimentos
		if(slowestTroops[0])
			if(validaLimites(getEscada()))
				setEscada(getEscada() - 1);
		if(slowestTroops[1])
			if(validaLimites(getAriete()))
				setAriete(getAriete() - 1);
		if(slowestTroops[2])
			if(validaLimites(getTorre()))
				setTorre(getTorre() - 1);
	}

	
	public int quantosEstaoProximos()  {
		int cont=0;
		if (escada == 0) cont++;
		if (ariete == 0) cont++;
		if (torre == 0)  cont++;
		return cont;
	}
	public int quemEstaProximo()  {
		int cont=0;
		if (escada == 0) cont=1;
		if (ariete == 0) cont=2;
		if (torre == 0)  cont=3;
		return cont;
	}
	public boolean algumStatusTrackaZero() {
		if (integridade == 0) return true;
		if (moral == 0) return true;
		if (provisoes == 0) return true;
		return false;
	}
	
	
	public int[] getEstadoLinhas() {
		int[] linhas = new int[11]; 

		linhas[0] = integridade;
		linhas[1] = moral;
		linhas[2] = provisoes;
		linhas[3] = tunel;
		linhas[4] = provisoesRoubadas;
		linhas[5] = escada;
		linhas[6] = ariete;
		linhas[7] = torre;
		linhas[8] = trebuchet;
		linhas[9] = diaAtual;
		linhas[10] = baralho.get(eventoAtual).getnrCarta();
		
		return linhas;
	}
	
	
	
	
	// 		-:- Getters and Setters -:-
	public int getEventoAtual() {
		return eventoAtual;
	}

	public void setEventoAtual(int eventoAtual) {
		this.eventoAtual = eventoAtual;
	}

	public int getDiaAtual() {
		return diaAtual;
	}

	public void setDiaAtual(int diaAtual) {
		this.diaAtual = diaAtual;
	}

	public List<Carta> getBaralho() {
		return baralho;
	}

	public void setBaralho(List<Carta> baralho) {
		this.baralho = baralho;
	}

	public int getModtrebuchet() {
		return modtrebuchet;
	}

	public void setModtrebuchet(int modtrebuchet) {
		this.modtrebuchet = modtrebuchet;
	}

	public int getModescada() {
		return modescada;
	}

	public void setModescada(int modescada) {
		this.modescada = modescada;
	}

	public int getModariete() {
		return modariete;
	}

	public void setModariete(int modariete) {
		this.modariete = modariete;
	}

	public int getModtorre() {
		return modtorre;
	}

	public void setModtorre(int modtorre) {
		this.modtorre = modtorre;
	}

	public int getModintegridade() {
		return modintegridade;
	}

	public void setModintegridade(int modintegridade) {
		this.modintegridade = modintegridade;
	}

	public int getModMoral() {
		return modMoral;
	}

	public void setModMoral(int modMoral) {
		this.modMoral = modMoral;
	}

	public int getModprovisoes() {
		return modprovisoes;
	}

	public void setModprovisoes(int modprovisoes) {
		this.modprovisoes = modprovisoes;
	}

	public int getIntegridade() {
		return integridade;
	}

	public void setIntegridade(int integridade) {
		this.integridade = integridade;
	}

	public int getMoral() {
		return moral;
	}

	public void setMoral(int moral) {
		this.moral = moral;
	}

	public int getProvisoes() {
		return provisoes;
	}

	public void setProvisoes(int provisoes) {
		this.provisoes = provisoes;
	}

	public int getTunel() {
		return tunel;
	}

	public void setTunel(int tunel) {
		this.tunel = tunel;
	}

	public int getProvisoesRoubadas() {
		return provisoesRoubadas;
	}

	public void setProvisoesRoubadas(int provisoesRoubadas) {
		this.provisoesRoubadas = provisoesRoubadas;
	}

	public int getTrebuchet() {
		return trebuchet;
	}

	public void setTrebuchet(int trebuchet) {
		this.trebuchet = trebuchet;
	}

	public int getEscada() {
		return escada;
	}

	public void setEscada(int escada) {
		this.escada = escada;
	}

	public int getAriete() {
		return ariete;
	}

	public void setAriete(int ariete) {
		this.ariete = ariete;
	}

	public int getTorre() {
		return torre;
	}

	public void setTorre(int torre) {
		this.torre = torre;
	}

	public int getModCoupure() {
		return modCoupure;
	}

	public void setModCoupure(int modCoupure) {
		this.modCoupure = modCoupure;
	}

	public int getModSabotage() {
		return modSabotage;
	}

	public void setModSabotage(int modSabotage) {
		this.modSabotage = modSabotage;
	}

	public int getModRaid() {
		return modRaid;
	}

	public void setModRaid(int modRaid) {
		this.modRaid = modRaid;
	}

	public boolean getOnlyRaidSabotage() {
		return onlyRaidSabotage;
	}

	public void setOnlyRaidSabotage(boolean onlyRaidSabotage) {
		this.onlyRaidSabotage = onlyRaidSabotage;
	}

	public int getModCloseCombat() {
		return modCloseCombat;
	}

	public void setModCloseCombat(int modCloseCombat) {
		this.modCloseCombat = modCloseCombat;
	}

	public int getModCircleSpaces() {
		return modCircleSpaces;
	}

	public void setModCircleSpaces(int modCircleSpaces) {
		this.modCircleSpaces = modCircleSpaces;
	}

	public int getPontosDisponiveis() {
		return pontosDisponiveis;
	}

	public void setPontosDisponiveis(int pontosDisponiveis) {
		this.pontosDisponiveis = pontosDisponiveis;
	}

	public boolean isTriggerBoilingWater() {
		return triggerBoilingWater;
	}

	public void setTriggerBoilingWater(boolean triggerBoilingWater) {
		this.triggerBoilingWater = triggerBoilingWater;
	}

	public void setEscolha(int escolha) {
		// TODO Auto-generated method stub
		this.escolha = escolha;
	}

	public int getEscolha() {
		// TODO Auto-generated method stub
		return escolha;
	}


}