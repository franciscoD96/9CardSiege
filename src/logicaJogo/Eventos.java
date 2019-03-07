package logicaJogo;

import java.io.Serializable;
import logicaJogo.cardsiege;

abstract class Eventos implements Serializable {
    String nomeEvento;
    String descricao;
    int pontosAcao;
    cardsiege jogo;

    public Eventos (String nome, String desc, int pontos, cardsiege referencia) {
        nomeEvento = nome;
        descricao = desc;
        pontosAcao = pontos;
        jogo = referencia;
    }

    public String toString() {
    	String frase = "";
    	frase += nomeEvento;
    	frase += "\n";
    	frase += descricao;
    	frase += "\n";
    	frase += "Pontos de Ação: " + pontosAcao + "\n";
        return frase;
    }
    
    public int ativaEvento() {
    	return -1;
    }
}

class Trebuchet extends Eventos {
    public Trebuchet(int pontos, cardsiege referencia){
        super("Trebuchet", "if T==3 > 2 Damage to wall\nif T==2 > 1 Damage to wall\nif T==1 && D6=={4,5,6} > 1 Dam", pontos, referencia);
    }
    
    public int ativaEvento() {
    	
    	switch (jogo.getTrebuchet()) {
    	case 1: 
    		if(Dado.lancamento() > 3)
    			if(jogo.validaLimites(jogo.getIntegridade()))
                    jogo.setIntegridade(jogo.getIntegridade() - 1);
    		break;
    	case 2:
    		if(jogo.validaLimites(jogo.getIntegridade()))
                    jogo.setIntegridade(jogo.getIntegridade() - 1);
    		break;
    	case 3:
    		if(jogo.validaLimites(jogo.getIntegridade()))
    			jogo.setIntegridade(jogo.getIntegridade() - 2);
    		if(jogo.getIntegridade() < 0) // não deixa ser menor que 0
    			jogo.setIntegridade(0);
    		break;
		default: 
			System.out.println("erro no trebuchet");
			break;	
    	}
    	return pontosAcao;
    }
}

class DeathofaLeader extends Eventos {
    public DeathofaLeader(cardsiege referencia) {
        super("Death of a Leader", "Reduce Morale by 1", 2, referencia);
    }
    
    public int ativaEvento() {
        
        if(jogo.validaLimites(jogo.getEscada()))
            jogo.setEscada(jogo.getEscada() - 1);
        if(jogo.validaLimites(jogo.getTorre()))
            jogo.setTorre(jogo.getTorre() - 1);
    	
        if(jogo.validaLimites(jogo.getIntegridade()))
            jogo.setMoral(jogo.getMoral() - 1);

    	return pontosAcao;
    }
}
class GateFortified extends Eventos {
	public GateFortified(cardsiege referencia) {
            super("Gate Fortified", "+1 to attacks on the Battering Ram", 2, referencia);   
	}
	public int ativaEvento() {
            
		if(jogo.validaLimites(jogo.getEscada()))
			jogo.setEscada(jogo.getEscada() - 1);
        if(jogo.validaLimites(jogo.getAriete()))
            jogo.setAriete(jogo.getAriete() - 1);
		
                
		jogo.setModariete(1);
		
		return pontosAcao;
	}
}
class FlamingArrows extends Eventos {
	public FlamingArrows(cardsiege referencia) {
		super("Flaming Arrows", "+1 to attacks on the Siege Engine", 3, referencia);
	}
	public int ativaEvento() {
		
        if(jogo.validaLimites(jogo.getTorre()))
        	jogo.setTorre(jogo.getTorre() - 1);
		
		jogo.setModtorre(1);
		
		return pontosAcao;
	}
}



class VolleyofArrows extends Eventos {
	public VolleyofArrows(cardsiege referencia) {
		super("Volley of Arrows", "+1 to all attacks", 3, referencia);
	}
	public int ativaEvento() {
		
        if(jogo.validaLimites(jogo.getAriete()))
        	jogo.setAriete(jogo.getAriete() - 1);
		
        jogo.setModescada(1);
        jogo.setModariete(1);
        jogo.setModtorre(1);
		
		return pontosAcao;
	}
}
class Collapsed extends Eventos {
	public Collapsed(cardsiege referencia) {
		super("Collapsed!", "Siege Tower removed from game if on starting space", 2, referencia);
	}
	public int ativaEvento() {
             
        if(jogo.validaLimites(jogo.getEscada()))
        	jogo.setEscada(jogo.getEscada() - 1);
        if(jogo.validaLimites(jogo.getAriete()))
        	jogo.setAriete(jogo.getAriete() - 1);
		
                //torre cai
		if (jogo.getTorre() == 4)  // 4 = starting space
			jogo.setTorre(-1);
		
		return pontosAcao;
	}
}
class RepairedTrebuchet extends Eventos {
	public RepairedTrebuchet(cardsiege referencia) {
		super("Repaired Trebuchet", "Add +1 trebuchet (max 3) | +1 to Coupure Action", 2, referencia);
	}
	public int ativaEvento() {
		
        if(jogo.validaLimites(jogo.getEscada()))
        	jogo.setEscada(jogo.getEscada() - 1);
        if(jogo.getTrebuchet() < 3)
        	jogo.setTrebuchet(jogo.getTrebuchet() + 1);
        
		// +1 to coupure action
        jogo.setModCoupure(jogo.getModCoupure() + 1);
                
		return pontosAcao; 
	}
}
class CoverofDarkness extends Eventos {
	public CoverofDarkness(cardsiege referencia) {
		super("Cover of Darkness", "+1 to Raid and Sabotage actions", 3, referencia);
	}
	public int ativaEvento() {
		
        jogo.moveSlowestTroops();
                
        jogo.setModRaid(1);
		jogo.setModSabotage(1);
		
		return pontosAcao; 
	}
}

class EnemyFatigue extends Eventos {
	public EnemyFatigue(cardsiege referencia) {
		super("Enemy Fatigue", "+1 to Coupure, Raid and Sabotage actions", 3, referencia);
	}
	public int ativaEvento() {
		
        if(jogo.validaLimites(jogo.getEscada()))
            jogo.setEscada(jogo.getEscada() - 1);
            
        jogo.setModCoupure(jogo.getModCoupure() + 1);
        jogo.setModRaid(jogo.getModRaid() + 1);
        jogo.setModSabotage(jogo.getModSabotage() + 1);
	
		return pontosAcao; 
	}
}

class Rally extends Eventos {
	public Rally(cardsiege referencia) {
		super("Rally!", "+1 to attacks and Close Combat or Circle Spaces", 3, referencia);
	}
	public int ativaEvento() {
		
        if(jogo.validaLimites(jogo.getAriete()))
            jogo.setAriete(jogo.getAriete() - 1);
        if(jogo.validaLimites(jogo.getTorre()))
            jogo.setTorre(jogo.getTorre() - 1);
        
        jogo.setModCloseCombat(1);
        jogo.setModCircleSpaces(1);

        return pontosAcao; 
	}
}


class SuppliesSpoiled extends Eventos {
	public SuppliesSpoiled(cardsiege referencia) {
		super("Supplies Spoiled", "Reduce Supplies by 1", 2, referencia);
	}
	public int ativaEvento() {
		
		if(jogo.validaLimites(jogo.getEscada()))
            jogo.setEscada(jogo.getEscada() - 1);
            
        if(jogo.validaLimites(jogo.getProvisoes()))
             jogo.setProvisoes(jogo.getProvisoes() - 1);
		
		return pontosAcao; 
	}
}

class BadWeather extends Eventos {
	public BadWeather(cardsiege referencia) {
		super("Bad Weather", "Only raid and sabotage actions allowed in this turn", 2, referencia);
	}
	public int ativaEvento() {
		
		
		jogo.setOnlyRaidSabotage(true);
		
		return pontosAcao; 
	}
}

class BoilingOil extends Eventos {
	public BoilingOil(cardsiege referencia) {
		super("Boiling Oil", "+ 2 attacks on enemy in circle spaces", 2, referencia);
	}
	public int ativaEvento() {
		
        if(jogo.validaLimites(jogo.getAriete()))
	       jogo.setAriete(jogo.getAriete() - 1);
	    if(jogo.validaLimites(jogo.getEscada()))
	       jogo.setEscada(jogo.getEscada() - 1);
	     
	    jogo.setModCircleSpaces(jogo.getModCircleSpaces() + 2);
		
		return pontosAcao; 
	}
}

class Illness extends Eventos {
	public Illness(cardsiege referencia) {
		super("Illness", "Reduce morale by 1 | Reduce supplies by 1", 2, referencia);
	}
	public int ativaEvento() {
            
        if(jogo.validaLimites(jogo.getTorre()))
            jogo.setTorre(jogo.getTorre() - 1);
         
        if(jogo.validaLimites(jogo.getProvisoes()))
            jogo.setProvisoes(jogo.getProvisoes() -1);
        if(jogo.validaLimites(jogo.getMoral()))
            jogo.setMoral(jogo.getMoral() - 1);
	
	
		return pontosAcao; 
	}
}

class GuardsDistracted extends Eventos {
	public GuardsDistracted(cardsiege referencia) {
		super("Guards Distracted", 
                        "+1 to sabotage action | +1 to morale actions", 2, referencia);
	}
	public int ativaEvento() {
            
		jogo.moveSlowestTroops();
    
        jogo.setModMoral(jogo.getModMoral() + 1);
        jogo.setModSabotage(jogo.getModSabotage() + 1);
		
		
		return pontosAcao; 
	}
}

class DeterminedEnemy extends Eventos {
	public DeterminedEnemy(cardsiege referencia) {
		super("Determined Enemy", "-1 to attacks on the Battlering Ram", 2, referencia);
	}
	public int ativaEvento() {
                        
    	if(jogo.validaLimites(jogo.getAriete()))
    		jogo.setAriete(jogo.getAriete() - 1);
		
        jogo.setModariete(-1);
		
		return pontosAcao; 
	}
}

class IronShields extends Eventos {
	public IronShields(cardsiege referencia) {
		super("Iron Shields", "-1 to attacks on the Siege Tower", 2, referencia);
	}
	public int ativaEvento() {
        
		if(jogo.validaLimites(jogo.getTorre()))
        	jogo.setTorre(jogo.getTorre() - 1);
      
        jogo.setModtorre(-1);
        
		return pontosAcao; 
	}
}


class Faith extends Eventos {
	public Faith(cardsiege referencia) {
		super("Faith", "+1 to attacks on the BAttering Ram, Ladders and Morale Action", 3, referencia);
	}
	public int ativaEvento() {
            
        if(jogo.validaLimites(jogo.getTorre()))
        	jogo.setTorre(jogo.getTorre() - 1); 
        if(jogo.validaLimites(jogo.getAriete()))
            jogo.setAriete(jogo.getAriete() - 1);
        if(jogo.validaLimites(jogo.getEscada()))
             jogo.setEscada(jogo.getEscada() - 1);

        jogo.setModariete(jogo.getModariete() + 1);
        jogo.setModescada(jogo.getModescada() + 1);
        jogo.setModMoral(jogo.getModMoral() + 1);
		
		return pontosAcao; 
	}
}

