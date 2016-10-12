package com.jouwee.po.model;

/**
 *
 * @author Nícolas Pohren
 */
public enum Objetivo {
    
    /** Maximizar */
    MAXIMIZAR("Max"),
    /** Minimizar */
    MINIMIZAR("Min");
    
    /** Nome do objetivo */
    private final String name;

    /**
     * Cria um novo objetivo
     * 
     * @param name 
     */
    private Objetivo(String name) {
        this.name = name;
    }

    /**
     * Retorna o nome do objetivo
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
    
}
