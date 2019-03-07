/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.states;

public interface IStates {
    	
    //inicio jogo
    IStates esperaPorCarta();
    IStates lancamentoCarta();
    
    //ataques
    IStates escolhaLinhadoInimigo();
    IStates ataqueArqueiros(int linhaEscolhida);
    IStates ataqueAguaQuente(int linhaEscolhida);
    IStates ataqueCorpoACorpo(int linhaEscolhida);
    
    //faz parte do escolha accao
    IStates escolheAcao();
    IStates voltaEscolheAcao();
    IStates repararMuralha();
    IStates discursoMotivacional();
    IStates menosProvisoesPorMaisMoral();
    IStates roubarMantimentos();
    IStates sabotarInimigo ();
    IStates passarTurno();
    
    //tunel
    IStates escolhePosicaoTunel();
    IStates movimentoTunel(int posicaoEscolhida);
    IStates voltaDepoisEscolherTunel();
    
    //faz parte do esperaCompraAP
    IStates esperaCompraAP();
    IStates comprouAP();
    IStates compraAPporMoral();
    IStates compraAPporProvisoes();
    IStates naoComprouAP();
    
    //pontosRelated
    IStates ficouSemPontos();
    IStates voltaSemPontos();
    
    //rally troops
    IStates escolheRallyTroops();
    IStates fazDiscursoMotivacional();
    IStates fazDiscurso();
    IStates ofereceSupplies();

    
    //gameOver 
    IStates gameOver();
    IStates terminarJogo();
    IStates iniciarNovoJogo();
    IStates sairDoJogo();
    IStates perdeuOJogo();
    IStates acabouComOJogo();

	
}
