package com.jouwee.po.model;

import com.jouwee.commons.mvc.Model;
import com.jouwee.commons.mvc.PropertyEvent;
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
     */
    public SimplexTableauModel() {
        this (new ArrayList<>());
    }

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
        fireEvent(new PropertyEvent("lines", null, lines));
    }

    /**
     * Retorna a linha da variável básica
     * 
     * @param variavel
     * @return SimplexTableauLine
     */
    public SimplexTableauLine getLine(Variavel variavel) {
        for (SimplexTableauLine line : lines) {
            if (line.getVariavel().equals(variavel)) {
                return line;
            }
        }
        return null;
    }
    
    /**
     * Retorna a linha da função objetiva
     * 
     * @return SimplexTableauLine
     */
    public SimplexTableauLine getLineFuncaoObjetivo() {
        if (lines.isEmpty()) {
            return new SimplexTableauLine();
        }
        return lines.get(0);
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
     * Retorna uma variável do modelo
     * 
     * @param name
     * @return Variavel
     */
    public Variavel getVariavel(String name) {
        for (Variavel variavel : variaveis) {
            if (variavel.getName().equals(name)) {
                return variavel;
            }
        }
        return null;
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
