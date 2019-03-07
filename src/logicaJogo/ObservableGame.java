package logicaJogo;

import java.util.Observable;

import logicaJogo.ModelGame;
import model.states.IStates;
import model.states.escolhaLinhadoInimigo;
import model.states.escolheAcao;


public class ObservableGame extends Observable{

	ModelGame gameModel;//nossaClassChamadaGame;
	
	public ObservableGame() {
		gameModel = new ModelGame();
	}
	
	public void setGameModel(ModelGame gameModel)
    {        
        this.gameModel = gameModel;
        
        setChanged();
        notifyObservers();
    }   
	
	public void quit()
    {
        //gameModel.quit();
        
        setChanged();
        notifyObservers();
    }

	public ModelGame get() {
		// TODO Auto-generated method stub
		return gameModel;
	}
//    public IStates getState()
//    {
//        return gameModel.getState();
//    }     
	
	
	 // Metodos para a user interface
	
	public void escolhaLinhadoInimigo(int tipoDeAtaque){
        gameModel.escolhaLinhadoInimigo(tipoDeAtaque);
        
        setChanged();
        notifyObservers();
    }
	
	public void lancamentoCarta(){
        gameModel.lancamentoCarta();
        
        setChanged();
        notifyObservers();
    }
	
	public void roubarMantimentos(){
        gameModel.roubarMantimentos();
        
        setChanged();
        notifyObservers();
    }
	
	public void sabotarInimigo(){
        gameModel.sabotarInimigo();
        
        setChanged();
        notifyObservers();
    }
	
	
	public void repararMuralha(){
        gameModel.repararMuralha();
        
        setChanged();
        notifyObservers();
    }
	
	public void discursoMotivacional(){
        gameModel.discursoMotivacional();
        
        setChanged();
        notifyObservers();
    }
	
	public void menosProvisoesPorMaisMoral(){
        gameModel.menosProvisoesPorMaisMoral();
        
        setChanged();
        notifyObservers();
    }
	
	public void voltaEscolheAcao(){
        gameModel.voltaEscolheAcao();
        
        setChanged();
        notifyObservers();
    }
	
	public void passarTurno(){
        gameModel.passarTurno();
        
        setChanged();
        notifyObservers();
    }
	
	//muda de estado
	public void escolhePosicaoTunel(){
        gameModel.escolhePosicaoTunel();
        
        setChanged();
        notifyObservers();
    }
		
	public void movimentoTunel(int posicaoEscolhida){
        gameModel.movimentoTunel(posicaoEscolhida);
        
        setChanged();
        notifyObservers();
    }
	
	public void voltaSemPontos(){
        gameModel.voltaSemPontos();
        
        setChanged();
        notifyObservers();
    }
	
	public void ficouSemPontos(){
        gameModel.ficouSemPontos();
        
        setChanged();
        notifyObservers();
    }
	
	
	public void comprouAP(){
        gameModel.comprouAP();
        
        setChanged();
        notifyObservers();
    }
	
	public void compraAPporMoral(){
        gameModel.compraAPporMoral();
        
        setChanged();
        notifyObservers();
    }
	
	public void compraAPporProvisoes(){
        gameModel.compraAPporProvisoes();
        
        setChanged();
        notifyObservers();
    }
	
	public void naoComprouAP(){
        gameModel.naoComprouAP();
        
        setChanged();
        notifyObservers();
    }
	
	public void perdeuOJogo(){
      //  gameModel.perdeuOJogo();
        
        setChanged();
        notifyObservers();
    }
	
	public void acabouComOJogo(){
        //gameModel.acabouComOJogo();
        
        setChanged();
        notifyObservers();
    }
	
	public void terminarJogo(){
       // gameModel.terminarJogo();
        
        setChanged();
        notifyObservers();
    }
	
	public void gameOver(){
       // gameModel.gameOver();
        
        setChanged();
        notifyObservers();
    }
	
	public void iniciarNovoJogo(){
        gameModel = new ModelGame();
        
        setChanged();
        notifyObservers();
    }
	
	public void sairDoJogo(){
      //  gameModel.sairDoJogo();
        
        setChanged();
        notifyObservers();
    }
	
	public void realizaAtaque(int inimigoEscolhido) {
		
		switch(gameModel.getEscolha() ) { // o tipo de ataque tá aqui
		case 1: 
			gameModel.ataqueArqueiros(inimigoEscolhido);
	        setChanged();
	        notifyObservers();
	        break;
		case 2: 
			gameModel.ataqueAguaQuente(inimigoEscolhido);
	        setChanged();
	        notifyObservers();
	        break;
		case 3: 
			gameModel.ataqueCorpoACorpo(inimigoEscolhido);
	        setChanged();
	        notifyObservers();
	        break;
		}
		
        setChanged();
        notifyObservers();
	}

	public void closeCombat1(){
		gameModel.escolhaLinhadoInimigo(3);
        gameModel.ataqueCorpoACorpo(this.get().getCardsiege().quemEstaProximo());
         
		
        
        setChanged();
        notifyObservers();
    }
	public void fazDiscurso(){
        gameModel.fazDiscurso();
        
        setChanged();
        notifyObservers();
    }
	public void ofereceSupplies(){
        gameModel.ofereceSupplies();
        
        setChanged();
        notifyObservers();
    }
    
}
