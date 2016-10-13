package com.jouwee.po.model;

import com.jouwee.commons.math.Expression;
import com.jouwee.commons.mvc.Model;

/**
 * Função objetivo
 * 
 * @author Nícolas Pohren
 */
public class FuncaoObjetivo implements Model {

    /** Objetivo */
    private Objetivo objetivo;
    /** Equacao da função objetivo */
    private Expression equacao;

    /**
     * Cria uma nova função objetivo
     */
    public FuncaoObjetivo() {
        this(Objetivo.MAXIMIZAR, new Expression());
    }

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
     * Define o objetivo
     * 
     * @param objetivo 
     */
    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }
   
    /**
     * Retorna a equacao
     * 
     * @return ExpressionNode
     */
    public Expression getEquacao() {
        return equacao;
    }

    /**
     * Define a equação
     * 
     * @param equacao 
     */    
    public void setEquacao(Expression equacao) {
        this.equacao = equacao;
    }
    
}
