package com.jouwee.po.model;

import com.jouwee.commons.mvc.EventListener;
import com.jouwee.commons.mvc.Model;
import com.jouwee.commons.mvc.PropertyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de um problema linear
 * 
 * @author Nícolas Pohren
 */
public class ModeloProblemaLinear implements Model {

    /** Variáveis do problema */
    private final Variaveis variaveis;
    /** Restrições */
    private final List<Restricao> restricoes;
    /** Redirecionador de eventos */
    private final EventRedirector redirecionador;
    /** Função para resolução */
    private FuncaoObjetivo funcaoObjetivo;

    /**
     * Cria um novo modelo de problema linear
     */
    public ModeloProblemaLinear() {
        variaveis = new Variaveis();
        funcaoObjetivo = new FuncaoObjetivo();
        restricoes = new ArrayList<>();
        redirecionador = new EventRedirector();
    }
    
    /**
     * Retorna as variáveis
     * 
     * @return Variaveis
     */
    public Variaveis getVariaveis() {
        return variaveis;
    }

    /**
     * Define a função objetivo
     * 
     * @param funcaoObjetivo 
     */
    public void setFuncaoObjetivo(FuncaoObjetivo funcaoObjetivo) {
        this.funcaoObjetivo = funcaoObjetivo;
    }
    
    /**
     * Returna a função objetivo
     * 
     * @return FuncaoObjetivo
     */
    public FuncaoObjetivo getFuncaoObjetivo() {
        return funcaoObjetivo;
    }

    /**
     * Retorna as descrições
     * 
     * @return {@code List<Restricao>}
     */
    public List<Restricao> getRestricoes() {
        return new ArrayList<>(restricoes);
    }
    
    /**
     * Adiciona uma restrição
     * 
     * @param restricao 
     */
    public void addRestricao(Restricao restricao) {
        restricoes.add(restricao);
        fireEvent(new PropertyEvent("restricoes", null, restricoes));
        restricao.addEventListener(redirecionador);
    }
    
    /**
     * Redirecionador de eventos
     */
    private class EventRedirector implements EventListener<PropertyEvent> {

        @Override
        public void observed(PropertyEvent event) {
            ModeloProblemaLinear.this.fireEvent(event);
        }
        
    }
    
}
