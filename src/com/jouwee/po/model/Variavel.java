package com.jouwee.po.model;

import com.jouwee.commons.mvc.Model;
import com.jouwee.commons.mvc.PropertyEvent;
import java.util.Objects;

/**
 * Variável de decisão
 * 
 * @author Nícolas Pohren
 */
public class Variavel implements Model {
    
    /** Nome da variável */
    private String name;
    /** Descrição da variável */
    private String descricao;
    /** Indica se a variável é da função objetivo */
    private final boolean funcaoObjetivo;
    /** Restrição que esta restrição é folga */
    private final Restricao restricaoFolga;

    /**
     * Cria uma nova variável
     */
    public Variavel() {
        this("", "", null, false);
    }
    
    /**
     * Cria uma nova variável
     * 
     * @param name
     */
    public Variavel(String name) {
        this(name, "", null, false);
    }
    
    /**
     * Cria uma nova variável
     * 
     * @param name
     * @param descricao 
     */
    public Variavel(String name, String descricao) {
        this(name, descricao, null, false);
    }
    
    /**
     * Cria uma nova variável
     * 
     * @param name
     * @param descricao 
     * @param restricaoFolga
     */
    public Variavel(String name, String descricao, Restricao restricaoFolga) {
        this(name, descricao, restricaoFolga, false);
    }
    
    /**
     * Cria uma nova variável
     * 
     * @param name
     * @param descricao 
     * @param funcaoObjetivo
     */
    public Variavel(String name, String descricao, boolean funcaoObjetivo) {
        this(name, descricao, null, funcaoObjetivo);
    }
    
    /**
     * Cria uma nova variável
     * 
     * @param name
     * @param descricao 
     * @param restricaoFolga
     * @param funcaoObjetivo
     */
    public Variavel(String name, String descricao, Restricao restricaoFolga, boolean funcaoObjetivo) {
        this.name = name;
        this.descricao = descricao;
        this.restricaoFolga = restricaoFolga;
        this.funcaoObjetivo = funcaoObjetivo;
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
     * Define o nome da variável
     * 
     * @param name 
     */
    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        fireEvent(new PropertyEvent("name", oldValue, name));
    }

    /**
     * Retorna a descrição da variável
     * 
     * @return String
     */
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Define a descrição
     * 
     * @param descricao 
     */
    public void setDescricao(String descricao) {
        String oldValue = this.descricao;
        this.descricao = descricao;
        fireEvent(new PropertyEvent("descricao", oldValue, descricao));
    }

    /**
     * Retorna a restrição que esta variável é folga
     * 
     * @return Restricao
     */
    public Restricao getRestricaoFolga() {
        return restricaoFolga;
    }
    
    /**
     * Retorna se a variável é auxiliar
     * 
     * @return boolean
     */
    public boolean isAuxiliar() {
        return funcaoObjetivo || restricaoFolga != null;
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
