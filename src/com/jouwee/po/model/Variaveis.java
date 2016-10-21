package com.jouwee.po.model;

import com.jouwee.commons.mvc.EventListener;
import com.jouwee.commons.mvc.Model;
import com.jouwee.commons.mvc.PropertyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Variáveis de um problema
 * 
 * @author Nícolas Pohren
 */
public class Variaveis implements Model {

    /** Variáveis */
    public final List<Variavel> variaveis;
    /** Redirecionador de eventos */
    private final EventRedirector redirecionador;

    /**
     * Cria as variáveis do programa
     */
    public Variaveis() {
        variaveis = new ArrayList<>();
        redirecionador = new EventRedirector();
    }
    
    /**
     * Adiciona uma variável à lista
     * 
     * @param variavel 
     */
    public void add(Variavel variavel) {
        variaveis.add(variavel);
        fireEvent(new PropertyEvent("variaveis", null, variaveis));
        variavel.addEventListener(redirecionador);
    }
    
    /**
     * Remove uma variável da lista
     * 
     * @param variavel 
     */
    public void remove(Variavel variavel) {
        variaveis.remove(variavel);
        fireEvent(new PropertyEvent("variaveis", null, variaveis));
//        variavel.removeEventListener(redirecionador);
    }
    
    /**
     * Retorna a lista de variáveis
     * 
     * @return {@code List<Variavel>}
     */
    public List<Variavel> getVariaveis() {
        return new ArrayList<>(variaveis);
    }
    
    /**
     * Redirecionador de eventos
     */
    private class EventRedirector implements EventListener<PropertyEvent> {

        @Override
        public void observed(PropertyEvent event) {
            Variaveis.this.fireEvent(event);
        }
        
    }
    
}
