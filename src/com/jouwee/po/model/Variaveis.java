package com.jouwee.po.model;

import com.jouwee.commons.mvc.Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Variáveis de um problema
 * 
 * @author Nícolas Pohren
 */
public class Variaveis implements Model {

    /** Variáveis */
    public final Map<String, Variavel> variaveis;

    /**
     * Cria as variáveis do programa
     */
    public Variaveis() {
        variaveis = new HashMap<>();
    }
    
    /**
     * Adiciona uma variável à lista
     * 
     * @param variavel 
     */
    public void add(Variavel variavel) {
        variaveis.put(variavel.getName(), variavel);
    }
    
    /**
     * Retorna a lista de variáveis
     * 
     * @return {@code List<Variavel>}
     */
    public List<Variavel> getVariaveis() {
        return new ArrayList<>(variaveis.values());
    }
    
}
