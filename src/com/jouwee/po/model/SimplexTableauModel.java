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
    /** Iteração anterior */
    private final SimplexTableauModel iteracaoAnterior;
    
    /**
     * Cria um novo tableau do Simplex
     * 
     * @param iteracaoAnterior
     */
    public SimplexTableauModel(SimplexTableauModel iteracaoAnterior) {
        this (iteracaoAnterior, new ArrayList<>());
    }

    /**
     * Cria um novo tableau do Simplex
     * 
     * @param iteracaoAnterior
     * @param variaveis
     */
    public SimplexTableauModel(SimplexTableauModel iteracaoAnterior, List<Variavel> variaveis) {
        lines = new ArrayList<>();
        this.iteracaoAnterior = iteracaoAnterior;
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
     * Retorna uma variável do modelo
     * 
     * @param restricao
     * @return Variavel
     */
    public Variavel getVariavel(Restricao restricao) {
        for (Variavel variavel : variaveis) {
            if (restricao.equals(variavel.getRestricaoFolga())) {
                return variavel;
            }
        }
        return null;
    }
    
    /**
     * Retorna a variável da função objetivo
     * 
     * @return Variavel
     */
    public Variavel getVariavelFuncaoObjetivo() {
        return getVariavel("x0");
    }
    
    /**
     * Retorna a lista de variáveis
     * 
     * @return String
     */
    public List<Variavel> getVariaveis() {
        return new ArrayList<>(variaveis);
    }
    
    /**
     * Retorna as variávei auxiliares
     * 
     * @return {@code List<Variavel>}
     */
    public List<Variavel> getVariaveisAuxiliares() {
        List<Variavel> ret = new ArrayList<>();
        for (Variavel variavel : variaveis) {
            if (variavel.isAuxiliar()) {
                ret.add(variavel);
            }
        }
        return ret;
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

    /**
     * Retorna a iteração
     * 
     * @return SimplexTableauModel
     */
    public SimplexTableauModel getIteracaoAnterior() {
        return iteracaoAnterior;
    }

    /**
     * Retorna o valor da função objetivo
     * 
     * @return Double
     */
    public Double getValorFuncaoObjetivo() {
        return getLineFuncaoObjetivo().getValor();
    }

    /**
     * Retorna o valor de uma variável
     * 
     * @param variavel
     * @return Double
     */
    public double getValor(Variavel variavel) {
        SimplexTableauLine line = getLine(variavel);
        if (line == null) {
            return 0;
        }
        return line.getValor();
    }

    /**
     * Obtém a folga de uma restrição
     * 
     * @param restricao
     * @return double
     */
    public double getFolga(Restricao restricao) {
        return getValor(getVariavel(restricao));
    }
    
}
