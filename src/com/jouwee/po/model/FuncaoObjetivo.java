package com.jouwee.po.model;

import com.jouwee.commons.math.Function;

/**
 * Função objetivo
 * 
 * @author Nícolas Pohren
 */
public class FuncaoObjetivo {

    /** Objetivo */
    private final Objetivo objetivo;
    /** Equacao da função objetivo */
    private final Function equacao;

    /**
     * Cria uma nova função objetivo
     * 
     * @param objetivo
     * @param equacao 
     */
    public FuncaoObjetivo(Objetivo objetivo, Function equacao) {
        this.objetivo = objetivo;
        this.equacao = equacao;
    }

    /**
     * Retorna o objetivo
     * 
     * @return Objetivo
     */
    public Objetivo getObjetivo() {
        return objetivo;
    }

    /**
     * Retorna a equacao
     * 
     * @return Function
     */
    public Function getEquacao() {
        return equacao;
    }
    
}
