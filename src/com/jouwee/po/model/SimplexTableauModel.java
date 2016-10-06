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
    
}
