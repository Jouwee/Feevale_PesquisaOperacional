package com.jouwee.po.model;

import com.jouwee.commons.math.Expression;
import com.jouwee.commons.math.ExpressionNode;

/**
 * Função objetivo
 * 
 * @author Nícolas Pohren
 */
public class FuncaoObjetivo {

    /** Objetivo */
    private final Objetivo objetivo;
    /** Equacao da função objetivo */
    private final Expression equacao;

    /**
     * Cria uma nova função objetivo
     * 
     * @param objetivo
     * @param equacao 
     */
    public FuncaoObjetivo(Objetivo objetivo, Expression equacao) {
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
     * @return ExpressionNode
     */
    public Expression getEquacao() {
        return equacao;
    }
    
}
