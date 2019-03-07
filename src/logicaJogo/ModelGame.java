package logicaJogo;

import java.io.Serializable;
import model.states.IStates;
import model.states.escolheAcao;
import model.states.esperaCompraAP;
import model.states.esperaPorCarta;

public class ModelGame implements Serializable{
    private cardsiege game;
    private IStates state;
    
    public ModelGame(){
        game = new cardsiege();
        state = new esperaPorCarta(game);
    }
    public cardsiege getCardsiege() {
        return game;
    }
    public void setDataGame(cardsiege dataCardsige) {
        this.game = dataCardsige;
    }
    public IStates getState() {
        return state;
    }
    public void setState(IStates state) {
        this.state = state;
    }    
    public int getEscolha() {
    	return game.getEscolha();
    }
    public void setEscolha(int escolha) {
    	game.setEscolha(escolha);
    }
	public int[] getEstadoLinhas() {
		return game.getEstadoLinhas();
	}
    
    //		-:- métodos que interagem com o cardsiege


	
    public boolean chegouFimdoDia() { 
        if (game.getEventoAtual() == 7) return true;
        else return false;
    }
	    
    public String toString() { 
    	 return game.toString(); 
    }
    
    public int verificaPontosAcao() {
    	return game.getPontosDisponiveis();
    }
    
    public int getUltimoLancamento() {
    	return game.getUltimoLancamento();
    }
    public String getNomeCartaAtual() {
    	return game.imprimeEventoAtual();
    }
    
	public boolean adicionaModMoral() {
        return this.game.adicionaModMoral();
    }
    
    public void finalDeTurno() {
    	if(game.getTunel() == 3)  //enemy line check
    		if (Dado.lancamento() == 1) {
    			if (game.getMoral() > 0)
    				game.setMoral(game.getMoral() - 1);
    			game.setProvisoesRoubadas(0);
    			game.setTunel(0);
    		}
    	
    	int nrInimigosCloseCombat = 0;
    	for (int i = 0; i < 3; i ++)
    		if (game.verificaAtaqueCorpoACorpo(i))
    			nrInimigosCloseCombat++;
    	
    	if (nrInimigosCloseCombat >= 2 || game.algumStatusTrackaZero()) {
    		if (state instanceof escolheAcao) 
    			setState(getState().perdeuOJogo());
    		if (state instanceof esperaCompraAP)
    			setState(getState().acabouComOJogo());
    		return;
    	}
    	if (game.getEventoAtual() == 6) 
    		game.fimdeDia();
    	else
    		game.setEventoAtual(game.getEventoAtual() + 1);
    }
    
// 			-:-métodos que fazem trigger da máquina de estados
     
    public void lancamentoCarta() {
        setEscolha(-1);//faz reset á escolha
    	setState(getState().lancamentoCarta());
    }
    
    public void passarTurno() {
    	finalDeTurno();
    	setState(getState().passarTurno());
    }
    
    public void voltaSemPontos() { // depois de atacar por algum dos métodos
    	setState(getState().voltaSemPontos());
    }
    
    public void voltaEscolheAcao() { // dentro do escolher linha, voltar atrás
        setEscolha(0);//tambem e usado para o volta atras tunel
    	setState(getState().voltaEscolheAcao());
    }
    
    //ataques
    public void escolhaLinhadoInimigo(int tipoDeAtaque){
        setEscolha(tipoDeAtaque);//talvez tirar nao sei, por agora e melhor ficar
        setState(getState().escolhaLinhadoInimigo());
    }
    public void ataqueArqueiros(int linhaEscolhida) {
    	setState(getState().ataqueArqueiros(linhaEscolhida));
    }
    public void ataqueAguaQuente(int linhaEscolhida){
        setState(getState().ataqueAguaQuente(linhaEscolhida));
    }
    public void ataqueCorpoACorpo(int linhaEscolhida){
        setState(getState().ataqueCorpoACorpo(linhaEscolhida));
    }
    
    //escolheAccao ainda
    public void repararMuralha(){
        setState(getState().repararMuralha());
    }
    public void discursoMotivacional(){
        setState(getState().discursoMotivacional());
    }
    public void menosProvisoesPorMaisMoral() {
        setState(getState().menosProvisoesPorMaisMoral());
    }    
    
    //tunel
    public void escolhePosicaoTunel(){
        setState(getState().escolhePosicaoTunel());
    }
    public void movimentoTunel(int posicaoEscolhida){
        setState(getState().movimentoTunel(posicaoEscolhida));
    }
    
    public void compraAPporMoral(){
       setState(getState().compraAPporMoral()); 
    }
    public void compraAPporProvisoes() {
        setState(getState().compraAPporProvisoes()); 
    }
    
    public void comprouAP() {
    	setState(getState().comprouAP());
    }
    public void naoComprouAP() {
    	finalDeTurno();
    	setState(getState().naoComprouAP());
    }
    public void ficouSemPontos() {
    	setState(getState().ficouSemPontos());
    }
    
    public void iniciarNovoJogo() {
        game = new cardsiege();//tirar daqui
    	setState(getState().iniciarNovoJogo());
    }
    public void fazDiscurso() {
    	setState(getState().fazDiscurso());
    }
    public void ofereceSupplies(){
    	setState(getState().ofereceSupplies());
    }
    public void terminarPrograma() {
    	setState(getState().sairDoJogo());
    }
    public void roubarMantimentos () {
    	setState(getState().roubarMantimentos());
    }
    
    public void sabotarInimigo () {
    	setState(getState().sabotarInimigo());
    }


    
    
}
