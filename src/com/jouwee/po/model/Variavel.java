package com.jouwee.po.model;

import java.util.Objects;

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
     */
    public Variavel(String name) {
        this(name, "");
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Variavel other = (Variavel) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Variavel{" + "name=" + name + ", descricao=" + descricao + '}';
    }
    
}
