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
    private transient final ObservableList<SimplexTableauModel> iteracoes;
    /** Erro de validação */
    private transient String erroValidacao;
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
            return new SimplexTableauModel(null);
        } else {
            return iteracoes.get(0);
        }
    }
    
    /**
     * Retorna a iteração final
     * 
     * @return SimplexTableauModel
     */
    public SimplexTableauModel getIteracaoFinal() {
        if (iteracoes.isEmpty()) {
            return new SimplexTableauModel(null);
        } else {
            return iteracoes.get(iteracoes.size() - 1);
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

    /**
     * Retorna se tem erro de validação
     * 
     * @return boolean
     */
    public boolean hasErroValidacao() {
        return erroValidacao != null && !erroValidacao.isEmpty();
    }
    
    /**
     * Retorna o erro de validação que ocorreu
     * 
     * @return String
     */
    public String getErroValidacao() {
        return erroValidacao;
    }

    /**
     * Define o erro de validação
     * 
     * @param erroValidacao
     * @param args 
     */
    public void setErroValidacao(String erroValidacao, Object args) {
        setErroValidacao(String.format(erroValidacao, args));
    }
    
    public void setErroValidacao(String erroValidacao) {
        this.erroValidacao = erroValidacao;
        fireEvent(new PropertyEvent("erroValidacao", null, erroValidacao));
    }
    
}
