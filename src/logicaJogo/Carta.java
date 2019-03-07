package logicaJogo;

import java.io.Serializable;

class Carta implements Serializable{
    int nrCarta;
    Eventos []evento;

    public Carta(int nr, Eventos []evs) {
    	nrCarta = nr;
    	evento = evs;
    }

	public String toString() {
    	String frase = "";
    	for(Eventos evs: evento)
    		frase += evs;
    	return frase;
    }
    
    int eventoDia(int dia) {
    	return evento[dia].ativaEvento();
    }
    
    String imprimeEventoAtual(int diaAtual) {
    	return evento[diaAtual].toString();
    }
    
    int getnrCarta() {return nrCarta; }
}
