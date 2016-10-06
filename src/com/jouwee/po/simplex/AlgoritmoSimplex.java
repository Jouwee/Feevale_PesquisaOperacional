package com.jouwee.po.simplex;

import com.jouwee.po.model.SimplexModel;
import com.jouwee.po.model.SimplexTableauLine;
import com.jouwee.po.model.SimplexTableauModel;
import com.jouwee.po.model.Variavel;
import java.util.Arrays;
import java.util.List;

/**
 * Implementação do Algoritmo Simplex
 * 
 * @author Nícolas Pohren
 */
public class AlgoritmoSimplex {
    
    /** Modelo */
    public final SimplexModel model;

    /**
     * Cria uma implementação do Algoritmo simplex
     * 
     * @param model 
     */
    public AlgoritmoSimplex(SimplexModel model) {
        this.model = model;
    }
    
    /**
     * Executa o algoritmo Simplex
     */
    public void executa() {
        normalizaModelo();
    }

    /**
     * Normaliza o modelo
     */
    private void normalizaModelo() {
        Variavel x0 = new Variavel("x0", "");
        Variavel a = new Variavel("a", "");
        Variavel b = new Variavel("b", "");
        Variavel x1 = new Variavel("x1", "");
        Variavel x2 = new Variavel("x2", "");
        
        List<Variavel> variaveis = Arrays.asList(new Variavel[] {x0, a, b, x1, x2});
        
        SimplexTableauModel iteracao = new SimplexTableauModel(variaveis);
        SimplexTableauLine line = new SimplexTableauLine();
        line.setVariavel(x0);
        line.setValor(0);
        line.setCoeficiente(x0, 1);
        line.setCoeficiente(a, -0.2);
        line.setCoeficiente(b, -0.3);
        line.setCoeficiente(x1, 0);
        line.setCoeficiente(x2, 0);
        iteracao.addLine(line);
        line = new SimplexTableauLine();
        line.setVariavel(x1);
        line.setValor(14);
        line.setCoeficiente(x0, 0);
        line.setCoeficiente(a, 0.2);
        line.setCoeficiente(b,-0.4);
        line.setCoeficiente(x1, 1);
        line.setCoeficiente(x2, 0);
        iteracao.addLine(line);
        line = new SimplexTableauLine();
        line.setVariavel(x2);
        line.setValor(18);
        line.setCoeficiente(x0, 0);
        line.setCoeficiente(a, 0.4);
        line.setCoeficiente(b, 0.3);
        line.setCoeficiente(x1, 0);
        line.setCoeficiente(x2, 1);
        iteracao.addLine(line);
        model.addIteracao(iteracao);
    }
    
}
