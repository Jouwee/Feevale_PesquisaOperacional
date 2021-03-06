package com.jouwee.po.model;

import com.jouwee.commons.mvc.Model;

/**
 * Modelo para resolução gráfica de problemas
 * 
 * @author Nícolas Pohren
 */
public class ResolucaoGraficaModel implements Model {
    
    /** Enunciado da questão */
    private Enunciado enunciado;
    /** Modelo do problema */
    private ModeloProblemaLinear modeloProblema;

    /**
     * Cria o modelo de resolução gráfica
     */
    public ResolucaoGraficaModel() {
        enunciado = new Enunciado();
        modeloProblema = new ModeloProblemaLinear();
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
    
}
