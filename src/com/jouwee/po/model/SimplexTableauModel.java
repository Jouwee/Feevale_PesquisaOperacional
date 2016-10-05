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
    
    /** Linhas do tableau */
    private final List<SimplexTableauLine> lines;

    /**
     * Cria um novo tableau do Simplex
     */
    public SimplexTableauModel() {
        lines = new ArrayList<>();
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
    
}
