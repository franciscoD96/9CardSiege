/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.states;

import java.io.Serializable;

import logicaJogo.cardsiege;


public class StateAdapter implements IStates,Serializable {
    
    private cardsiege dataCardsiege;
    
    public StateAdapter(cardsiege dataCardsiege)//, DataGame dataGame)
    {
        this.dataCardsiege = dataCardsiege;
    }
    public cardsiege getDataCardsiege()
    {
        return dataCardsiege;
    }
    public void setDataCardsiege(cardsiege dataCardsiege)
    {
        this.dataCardsiege = dataCardsiege;
    }

    //inicio Jogo
    @Override
    public IStates esperaPorCarta()				{ return this; }
    @Override
    public IStates lancamentoCarta()                            { return this; }

    //ataques
    @Override
    public IStates escolhaLinhadoInimigo()                      { return this; }
    @Override
    public IStates ataqueArqueiros(int linhaEscolhida)          { return this; }
    @Override
    public IStates ataqueAguaQuente(int linhaEscolhida)         { return this; }
    @Override
    public IStates ataqueCorpoACorpo(int linhaEscolhida)        { return this; }

    //escolheAccao
    @Override
    public IStates escolheAcao() 				{ return this; }
    @Override
    public IStates roubarMantimentos()                          {return this;}
    @Override
    public IStates sabotarInimigo ()                            {return this;}
    @Override
    public IStates repararMuralha()                             {return this; }
    @Override
    public IStates discursoMotivacional()                       {return this; }
    @Override
    public IStates menosProvisoesPorMaisMoral()                 {return this;}
    @Override
    public IStates voltaEscolheAcao()                           { return this; }
    @Override
    public IStates passarTurno() 				{ return this; }
    @Override
    public IStates perdeuOJogo() 				{ return this; }

    //tunel
    @Override
    public IStates escolhePosicaoTunel()                        { return this; }
    @Override
    public IStates voltaDepoisEscolherTunel()                   { return this; }
    @Override
    public IStates movimentoTunel(int posicaoEscolhida)         { return this; }

    //sem pontos
    @Override
    public IStates voltaSemPontos()                             { return this; }
    @Override
    public IStates ficouSemPontos()                             { return this; }
    
    //rally troops
    @Override
    public IStates escolheRallyTroops(){ return this; }
    @Override
    public IStates fazDiscursoMotivacional(){ return this; }
    @Override
    public IStates fazDiscurso(){ return this; }
    @Override
    public IStates ofereceSupplies(){ return this; }

    //espera por AP
    @Override
    public IStates esperaCompraAP()				{ return this; }
    @Override
    public IStates comprouAP()					{ return this; }
    @Override
    public IStates compraAPporMoral()                           { return this; }
    @Override
    public IStates compraAPporProvisoes()                       { return this;}
    @Override
    public IStates naoComprouAP()				{ return this; }

    //gameOver
    @Override
    public IStates acabouComOJogo()				{ return this; }
    @Override
    public IStates terminarJogo()				{ return this; }
    @Override
    public IStates gameOver()					{ return this; }
    @Override
    public IStates iniciarNovoJogo()                            { return this; }
    @Override
    public IStates sairDoJogo()					{ return this; }
    
    

    
}