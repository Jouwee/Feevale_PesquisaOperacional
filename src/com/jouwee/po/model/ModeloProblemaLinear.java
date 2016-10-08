package com.jouwee.po.model;

import com.jouwee.commons.math.EquationParser;
import com.jouwee.commons.math.ExpressionParser;
import com.jouwee.commons.mvc.Model;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de um problema linear
 * 
 * @author Nícolas Pohren
 */
public class ModeloProblemaLinear implements Model {

    /** Variáveis do problema */
    private Variaveis variaveis;
    /** Função para resolução */
    private FuncaoObjetivo funcaoObjetivo;
    /** Restrições */
    private List<Restricao> restricoes;

    /**
     * Cria um novo modelo de problema linear
     */
    public ModeloProblemaLinear() {
        
        variaveis = new Variaveis();
        variaveis.add(new Variavel("a", "Quantidade investida no fundo AAA, em $1000"));
        variaveis.add(new Variavel("b", "Quantidade investida no fundo BB, em $1000"));
        
        funcaoObjetivo = new FuncaoObjetivo(Objetivo.MAXIMIZAR, new ExpressionParser().parse("0.07 * a + 0.09 * b"));
        
        restricoes = new ArrayList<>();
        restricoes.add(new Restricao("Valor total", new EquationParser().parse("a + b <= 100")));
        restricoes.add(new Restricao("Valor máximo a", new EquationParser().parse("b <= 30")));
        restricoes.add(new Restricao("Valor máximo b", new EquationParser().parse("a >= 2 * b")));
        restricoes.add(new Restricao("Não negatividade a", new EquationParser().parse("a >= 0")));
        restricoes.add(new Restricao("Não negatividade b", new EquationParser().parse("b >= 0")));
        
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
    }
    
}
