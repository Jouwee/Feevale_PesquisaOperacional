package com.jouwee.po.model;

import com.jouwee.commons.math.AbsoluteValueNode;
import com.jouwee.commons.math.DifferenceNode;
import com.jouwee.commons.math.Equation;
import com.jouwee.commons.math.EquationType;
import com.jouwee.commons.math.Function;
import com.jouwee.commons.math.SumNode;
import com.jouwee.commons.math.WeigthedVariableNode;
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
    private List<Restricao> restricao;

    /**
     * Cria um novo modelo de problema linear
     */
    public ModeloProblemaLinear() {
        
        variaveis = new Variaveis();
        variaveis.add(new Variavel("a", "Quantidade investida no fundo AAA, em $1000"));
        variaveis.add(new Variavel("b", "Quantidade investida no fundo BB, em $1000"));
        
        funcaoObjetivo = new FuncaoObjetivo(Objetivo.MAXIMIZAR, new Function(new SumNode(new WeigthedVariableNode("a", 0.07), new WeigthedVariableNode("b", 0.09))));
        
        restricao = new ArrayList<>();
        restricao.add(new Restricao("Valor total", new Equation(new Function(new SumNode(new WeigthedVariableNode("a", 1), new WeigthedVariableNode("b", 1))), new Function(new AbsoluteValueNode(100)), EquationType.EQUALS_TO)));
        restricao.add(new Restricao("Valor máximo a", new Equation(new Function(new DifferenceNode(new WeigthedVariableNode("a", 1), new WeigthedVariableNode("b", 2))), new Function(new AbsoluteValueNode(0)), EquationType.GREATER_THAN_OR_EQUALS_TO)));
        restricao.add(new Restricao("Valor máximo b", new Equation(new Function(new WeigthedVariableNode("b", 1)), new Function(new AbsoluteValueNode(30)), EquationType.LESSER_THAN_OR_EQUALS_TO)));
        restricao.add(new Restricao("Não negatividade a", new Equation(new Function(new WeigthedVariableNode("a", 1)), new Function(new AbsoluteValueNode(0)), EquationType.GREATER_THAN_OR_EQUALS_TO)));
        restricao.add(new Restricao("Não negatividade b", new Equation(new Function(new WeigthedVariableNode("b", 1)), new Function(new AbsoluteValueNode(0)), EquationType.GREATER_THAN_OR_EQUALS_TO)));
        
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
    public List<Restricao> getRestricao() {
        return restricao;
    }
    
}
