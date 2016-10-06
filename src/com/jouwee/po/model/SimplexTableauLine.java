package com.jouwee.po.model;

import com.jouwee.commons.mvc.Model;
import java.util.HashMap;
import java.util.Map;

/**
 * Linha do tableau do Simplex
 * 
 * @author Nícolas Pohren
 */
public class SimplexTableauLine implements Model {
    
    /** Coeficientes */
    private final Map<Variavel, Double> coeficientes;
    /** Variável */
    private Variavel variavel;
    /** Valor da variável */
    private double valor;

    /**
     * Cria uma nova linha do tableau
     */
    public SimplexTableauLine() {
        coeficientes = new HashMap<>();
    }

    /**
     * Retorna a variável
     * 
     * @return Variavel
     */
    public Variavel getVariavel() {
        return variavel;
    }

    /**
     * Define a variável
     * 
     * @param variavel 
     */
    public void setVariavel(Variavel variavel) {
        this.variavel = variavel;
    }

    /**
     * Retorna o valor
     * 
     * @return double
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor
     * 
     * @param valor 
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    /**
     * Define o coeficiente de uma variável
     * 
     * @param variavel
     * @param coeficiente 
     */
    public void setCoeficiente(Variavel variavel, double coeficiente) {
        coeficientes.put(variavel, coeficiente);
    }
    
    /**
     * Retorna o coeficiente
     * 
     * @param variavel
     * @return double
     */
    public double getCoeficiente(Variavel variavel) {
        return coeficientes.get(variavel);
    }
    
}
