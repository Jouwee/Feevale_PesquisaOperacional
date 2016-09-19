package com.jouwee.po.model;

import com.jouwee.commons.math.Equation;

/**
 * Restrição
 * 
 * @author Nícolas Pohren
 */
public class Restricao {

    /** Descrição */
    private final String descricao;
    /** Equação */
    private final Equation equacao;

    /**
     * Cria uma nova restrição
     * 
     * @param descricao
     * @param equacao 
     */
    public Restricao(String descricao, Equation equacao) {
        this.descricao = descricao;
        this.equacao = equacao;
    }

    /**
     * Retorna a descrição
     * 
     * @return String
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna a equação
     * 
     * @return Equation
     */
    public Equation getEquacao() {
        return equacao;
    }
    
}
