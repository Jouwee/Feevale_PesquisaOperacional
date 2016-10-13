package com.jouwee.po.model;

import com.jouwee.commons.math.EquationParser;
import com.jouwee.commons.math.ExpressionParser;
import com.jouwee.commons.mvc.Model;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de um problema linear
 * 
 * @author Nícolas Pohren
 */
public class ModeloProblemaLinear implements Model {

    /** Variáveis do problema */
    private final Variaveis variaveis;
    /** Restrições */
    private final List<Restricao> restricoes;
    /** Função para resolução */
    private FuncaoObjetivo funcaoObjetivo;

    /**
     * Cria um novo modelo de problema linear
     */
    public ModeloProblemaLinear() {
        variaveis = new Variaveis();
        funcaoObjetivo = new FuncaoObjetivo();
        restricoes = new ArrayList<>();
    }
    
    /**
     * Retorna as variáveis
     * 
     * @return Variaveis
     */
    public Variaveis getVariaveis() {
        return variaveis;
    }

    /**
     * Define a função objetivo
     * 
     * @param funcaoObjetivo 
     */
    public void setFuncaoObjetivo(FuncaoObjetivo funcaoObjetivo) {
        this.funcaoObjetivo = funcaoObjetivo;
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
    public List<Restricao> getRestricoes() {
        return new ArrayList<>(restricoes);
    }
    
    /**
     * Adiciona uma restrição
     * 
     * @param restricao 
     */
    public void addRestricao(Restricao restricao) {
        restricoes.add(restricao);
    }
    
}
