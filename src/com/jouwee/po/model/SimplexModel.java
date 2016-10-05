package com.jouwee.po.model;

import com.jouwee.commons.mvc.Model;
import java.util.ArrayList;
import java.util.List;

/**
 * Model simplex
 * 
 * @author Nícolas Pohren
 */
public class SimplexModel implements Model {
    
    /** Iterações para resolução do Simplex */
    private final List<SimplexTableauModel> iteracoes;
    /** Enunciado da questão */
    private Enunciado enunciado;
    /** Modelo do problema */
    private ModeloProblemaLinear modeloProblema;

    /**
     * Cria o modelo Simplex
     */
    public SimplexModel() {
        enunciado = new Enunciado();
        modeloProblema = new ModeloProblemaLinear();
        iteracoes = new ArrayList<>();
    }    

    /**
     * Retorna o enunciado
     * 
     * @return Enunciado
     */
    public Enunciado getEnunciado() {
        return enunciado;
    }

    /**
     * Define o enunciado
     * 
     * @param enunciado 
     */
    public void setEnunciado(Enunciado enunciado) {
        this.enunciado = enunciado;
    }

    /**
     * Retorna o modelo do problema
     * 
     * @return ModeloProblemaLinear
     */
    public ModeloProblemaLinear getModeloProblema() {
        return modeloProblema;
    }

    /**
     * Define o modelo do problema
     * 
     * @param modeloProblema 
     */
    public void setModeloProblema(ModeloProblemaLinear modeloProblema) {
        this.modeloProblema = modeloProblema;
    }
    
    /**
     * Adiciona uma iteração
     * 
     * @param iteracao 
     */
    public void addIteracao(SimplexTableauModel iteracao) {
        iteracoes.add(iteracao);
    }

    /**
     * Retorna as iterações
     * 
     * @return {@code List<SimplexTableauModel>}
     */
    public List<SimplexTableauModel> getIteracoes() {
        return new ArrayList<>(iteracoes);
    }
    
}
