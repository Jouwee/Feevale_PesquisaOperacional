package com.jouwee.po.model;

import com.jouwee.commons.mvc.Model;
import com.jouwee.commons.mvc.PropertyEvent;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Model simplex
 * 
 * @author Nícolas Pohren
 */
public class SimplexModel implements Model {
    
    /** Iterações para resolução do Simplex */
    private final ObservableList<SimplexTableauModel> iteracoes;
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
        iteracoes = FXCollections.observableArrayList();
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
     * Elimina as iterações
     */
    public void clearIteracoes() {
        iteracoes.clear();
        fireEvent(new PropertyEvent("iteracoes", null, iteracoes));
    }
    
    /**
     * Adiciona uma iteração
     * 
     * @param iteracao 
     */
    public void addIteracao(SimplexTableauModel iteracao) {
        iteracoes.add(iteracao);
        fireEvent(new PropertyEvent("iteracoes", null, iteracoes));
    }
    
    /**
     * Adiciona um listener as iterações
     * 
     * @param listener 
     */
    public void addListenerIteracoes(InvalidationListener listener) {
        iteracoes.addListener(listener);
    }

    /**
     * Retorna a etapa de normalização
     * 
     * @return SimplexTableauModel
     */
    public SimplexTableauModel getEtapaNormalizacao() {
        if (iteracoes.isEmpty()) {
            return new SimplexTableauModel();
        } else {
            return iteracoes.get(0);
        }
    }
    
    /**
     * Retorna as iterações
     * 
     * @return {@code List<SimplexTableauModel>}
     */
    public ObservableList<SimplexTableauModel> getIteracoes() {
        return FXCollections.observableArrayList(iteracoes);
    }
    
}
