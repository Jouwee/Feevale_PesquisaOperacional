package com.jouwee.po.model;

import java.util.List;

/**
 * Modelo de um problema linear
 * 
 * @author Nícolas Pohren
 */
class ModeloProblemaLinear {

    /** Variáveis do problema */
    private Variaveis variaveis;
    /** Função para resolução */
    private FuncaoObjetivo funcaoObjetivo;
    /** Restrições */
    private List<Restricao> restricao;
    
}
