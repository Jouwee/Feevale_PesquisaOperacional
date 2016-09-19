package com.jouwee.po.model;

/**
 * Variável de decisão
 * 
 * @author Nícolas Pohren
 */
public class Variavel {
    
    /** Nome da variável */
    private final String name;
    /** Descrição da variável */
    private final String descricao;

    /**
     * Cria uma nova variável
     */
    public Variavel() {
        this("", "");
    }

    /**
     * Cria uma nova variável
     * 
     * @param name
     * @param descricao 
     */
    public Variavel(String name, String descricao) {
        this.name = name;
        this.descricao = descricao;
    }

    /**
     * Retorna o nome da variável
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna a descrição da variável
     * 
     * @return String
     */
    public String getDescricao() {
        return descricao;
    }
    
}
