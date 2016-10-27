package com.jouwee.po.model;

import com.jouwee.commons.mvc.Model;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
     * Construtor auxiliar usado para testes unitários
     * 
     * @param variavel
     * @param valor
     * @param coeficientes 
     */
    public SimplexTableauLine(Variavel variavel, double valor, Map.Entry<Variavel, Double>... coeficientes) {
        this();
        this.variavel = variavel;
        this.valor = valor;
        for (Map.Entry<Variavel, Double> entry : coeficientes) {
            this.coeficientes.put(entry.getKey(), entry.getValue());
        }
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
     * Retorna os coeficientes
     * 
     * @return double
     */
    public Map<Variavel, Double> getCoeficientes() {
        return new HashMap<>(coeficientes);
    }
    
    /**
     * Retorna o coeficiente
     * 
     * @param variavel
     * @return double
     */
    public double getCoeficiente(Variavel variavel) {
        Double value = coeficientes.get(variavel);
        if (value == null) {
            return 0;
        }
        return value;
    }
    
    /**
     * Retorna o valor da divisão com a variável que entra na base. Se o coeficiente for inválido (0 ou negativo), 
     * retorna {@code null}
     * 
     * @param variavelQueEntraNaBase
     * @return Double
     */
    public Double getDivisao(Variavel variavelQueEntraNaBase) {
        if (getCoeficiente(variavelQueEntraNaBase) <= 0) {
            return null;
        }
        return valor / getCoeficiente(variavelQueEntraNaBase);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.coeficientes);
        hash = 17 * hash + Objects.hashCode(this.variavel);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimplexTableauLine other = (SimplexTableauLine) obj;
        if (!Objects.equals(this.coeficientes, other.coeficientes)) {
            return false;
        }
        if (!Objects.equals(this.variavel, other.variavel)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SimplexTableauLine{" + "coeficientes=" + coeficientes + ", variavel=" + variavel + ", valor=" + valor + '}';
    }
    
}
