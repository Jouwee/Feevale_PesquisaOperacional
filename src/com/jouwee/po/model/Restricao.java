package com.jouwee.po.model;

import com.jouwee.commons.math.AbsoluteValueNode;
import com.jouwee.commons.math.Equation;
import com.jouwee.commons.math.EquationType;
import com.jouwee.commons.math.VariableNode;
import com.jouwee.commons.mvc.Model;
import com.jouwee.commons.mvc.PropertyEvent;

/**
 * Restrição
 * 
 * @author Nícolas Pohren
 */
public class Restricao implements Model {

    /** Descrição */
    private String descricao;
    /** Equação */
    private Equation equacao;

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
     * Define a descrição
     * 
     * @param descricao 
     */
    public void setDescricao(String descricao) {
        String oldValue = this.descricao;
        this.descricao = descricao;
        fireEvent(new PropertyEvent("descricao", oldValue, descricao));
    }

    /**
     * Retorna a equação
     * 
     * @return Equation
     */
    public Equation getEquacao() {
        return equacao;
    }
    
    /**
     * Define equação
     * 
     * @param equacao 
     */
    public void setEquacao(Equation equacao) {
        Equation oldValue = this.equacao;
        this.equacao = equacao;
        fireEvent(new PropertyEvent("equacao", oldValue, equacao));
    }
    
    /**
     * Retorna se a restrição é de não negatividade
     * 
     * @return boolean
     */
    public boolean isNaoNegatividade() {
        if (equacao.getType() != EquationType.GREATER_THAN_OR_EQUALS_TO) {
            return false;
        }
        if (!(equacao.getLeftFunction() instanceof VariableNode)) {
            return false;
        }
        if (!(equacao.getRightFunction() instanceof AbsoluteValueNode)) {
            return false;
        }
        AbsoluteValueNode right = (AbsoluteValueNode) equacao.getRightFunction();
        if (right.getValue() != 0) {
            return false;
        }
        return true;
    }
    
}
