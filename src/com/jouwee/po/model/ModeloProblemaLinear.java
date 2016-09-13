package com.jouwee.po.model;

import com.jouwee.commons.mvc.Model;
import java.util.List;

/**
 * Modelo de um problema linear
 * 
 * @author Nícolas Pohren
 */
public class ModeloProblemaLinear implements Model {

    /** Variáveis do problema */
    private Variaveis variaveis;
    /** Função para resolução */
    private FuncaoObjetivo funcaoObjetivo;
    /** Restrições */
    private List<Restricao> restricao;

    /**
     * Retorna as variáveis
     * 
     * @return Variaveis
     */
    public Variaveis getVariaveis() {
        return variaveis;
    }

    /**
     * Returna a função objetivo
     * 
     * @return FuncaoObjetivo
     */
    public FuncaoObjetivo getFuncaoObjetivo() {
        return funcaoObjetivo;
    }

    /**
     * Retorna as descrições
     * 
     * @return {@code List<Restricao>}
     */
    public List<Restricao> getRestricao() {
        return restricao;
    }
    
}
