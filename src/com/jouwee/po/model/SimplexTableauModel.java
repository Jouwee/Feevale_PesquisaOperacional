package com.jouwee.po.model;

import com.jouwee.commons.mvc.Model;
import java.util.ArrayList;
import java.util.List;

/**
 * Tableau do Simplex
 * 
 * @author Nícolas Pohren
 */
public class SimplexTableauModel implements Model {
    
    /** Variaveis */
    private final List<Variavel> variaveis;
    /** Linhas do tableau */
    private final List<SimplexTableauLine> lines;
    /** Variável que vai entrar na base na próxima iteração */
    private Variavel entraNaBase;
    /** Variável que vai sair da base na próxima iteração */
    private Variavel saiDaBase;

    /**
     * Cria um novo tableau do Simplex
     * 
     * @param variaveis
     */
    public SimplexTableauModel(List<Variavel> variaveis) {
        lines = new ArrayList<>();
        this.variaveis = variaveis;
    }
    
    /**
     * Adiciona uma linha ao tableau
     * 
     * @param line 
     */
    public void addLine(SimplexTableauLine line) {
        lines.add(line);
    }

    /**
     * Retorna as linhas do tableau
     * 
     * @return {@code List<SimplexTableauLine>}
     */
    public List<SimplexTableauLine> getLines() {
        return new ArrayList<>(lines);
    }
    
    /**
     * Retorna a lista de variáveis
     * 
     * @return String
     */
    public List<Variavel> getVariables() {
        return new ArrayList<>(variaveis);
    }

    /**
     * Returna a variável que vai entrar na base na próxima iteração
     * 
     * @return Variavel
     */
    public Variavel getEntraNaBase() {
        return entraNaBase;
    }

    /**
     * Define a variável que vai entrar na base na próxima iteração
     * @param entraNaBase 
     */
    public void setEntraNaBase(Variavel entraNaBase) {
        this.entraNaBase = entraNaBase;
    }

    /**
     * Retorna a variável que vai sair da base na próxima iteração
     * 
     * @return Variavel
     */
    public Variavel getSaiDaBase() {
        return saiDaBase;
    }

    /**
     * Define a variável que vai sair da base na próxima iteração
     * @param saiDaBase 
     */
    public void setSaiDaBase(Variavel saiDaBase) {
        this.saiDaBase = saiDaBase;
    }
    
}
