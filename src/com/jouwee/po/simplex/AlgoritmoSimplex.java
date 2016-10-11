package com.jouwee.po.simplex;

import com.jouwee.po.model.SimplexModel;
import com.jouwee.po.model.SimplexTableauLine;
import com.jouwee.po.model.SimplexTableauModel;
import com.jouwee.po.model.Variavel;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Implementação do Algoritmo Simplex
 * 
 * @author Nícolas Pohren
 */
public class AlgoritmoSimplex {
    
    /** Modelo */
    public final SimplexModel model;
    /** Iteração atual */
    private SimplexTableauModel iteracao;

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
        iteracao = model.getIteracoes().get(0);
        while (!isIteracaoFinal()) {
            identificaVariavelQueEntraNaBase();
            identificaVariavelQueSaiDaBase();
            iteracao = criaProximaIteracao(iteracao);
            model.addIteracao(iteracao);
        }
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
        line.setValor(20);
        line.setCoeficiente(x0, 0);
        line.setCoeficiente(a, 0.2);
        line.setCoeficiente(b, 0.4);
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

    /**
     * Identifica a variável que entra na base
     */
    private void identificaVariavelQueEntraNaBase() {
        SimplexTableauLine funcaoObjetivo = iteracao.getLines().get(0);
        Variavel variavelMenorCoeficiente = iteracao.getVariables().get(0);
        for (Variavel variavel : iteracao.getVariables()) {
            if (funcaoObjetivo.getCoeficiente(variavel) <= funcaoObjetivo.getCoeficiente(variavelMenorCoeficiente)) {
                variavelMenorCoeficiente = variavel;
            }
        }
        iteracao.setEntraNaBase(variavelMenorCoeficiente);
    }
    
    /**
     * Identifica a variável que sai da base
     */
    private void identificaVariavelQueSaiDaBase() {
        Variavel variavelMenorDivisao = null;
        double menorDivisao = Double.MAX_VALUE;
        for (SimplexTableauLine line : iteracao.getLines()) {
            Double divisao = line.getDivisao(iteracao.getEntraNaBase());
            if (divisao != null && divisao <= menorDivisao) {
                menorDivisao = divisao;
                variavelMenorDivisao = line.getVariavel();
            }
        }
        iteracao.setSaiDaBase(variavelMenorDivisao);
    }

    /**
     * Cria a próxima iteração
     * 
     * @param iteracaoAnterior
     * @return SimplexTableauModel
     */
    private SimplexTableauModel criaProximaIteracao(SimplexTableauModel iteracaoAnterior) {
        SimplexTableauModel novaIteracao = new SimplexTableauModel(iteracaoAnterior.getVariables());
        SimplexTableauLine lineNovaBase = criaLinhaNovaBase(iteracaoAnterior);
        for (SimplexTableauLine line : iteracaoAnterior.getLines()) {
            if (line.getVariavel().equals(iteracaoAnterior.getSaiDaBase())) {
                novaIteracao.addLine(lineNovaBase);
            } else {
                novaIteracao.addLine(recalculaLinha(line, lineNovaBase, iteracaoAnterior.getEntraNaBase()));
            }
        }
        return novaIteracao;
    }

    /**
     * Cria a linha da nova base
     * 
     * @param iteracaoAnterior
     * @return SimplexTableauLine
     */
    private SimplexTableauLine criaLinhaNovaBase(SimplexTableauModel iteracaoAnterior) {
        SimplexTableauLine linhaQueSai = iteracaoAnterior.getLine(iteracaoAnterior.getSaiDaBase());
        SimplexTableauLine linhaQueEntra = new SimplexTableauLine();
        double coeficiente = linhaQueSai.getCoeficiente(iteracaoAnterior.getEntraNaBase());
        linhaQueEntra.setVariavel(iteracaoAnterior.getEntraNaBase());
        linhaQueEntra.setValor(linhaQueSai.getValor() / coeficiente);
        for (Map.Entry<Variavel, Double> entry : linhaQueSai.getCoeficientes().entrySet()) {
            linhaQueEntra.setCoeficiente(entry.getKey(), entry.getValue() / coeficiente);
        }
        return linhaQueEntra;
    }

    /**
     * Recalcula a linha baseada no Pivo
     * 
     * @param linha
     * @param pivo 
     */
    private SimplexTableauLine recalculaLinha(SimplexTableauLine linha, SimplexTableauLine pivo, Variavel entraNaBase) {
        SimplexTableauLine novaLinha = new SimplexTableauLine();
        novaLinha.setVariavel(linha.getVariavel());
        double fator = -(linha.getCoeficiente(entraNaBase) / pivo.getCoeficiente(entraNaBase));
        novaLinha.setValor(linha.getValor() + pivo.getValor() * fator);
        for (Map.Entry<Variavel, Double> entry : linha.getCoeficientes().entrySet()) {
            novaLinha.setCoeficiente(entry.getKey(), linha.getCoeficiente(entry.getKey()) + pivo.getCoeficiente(entry.getKey()) * fator);
        }
        return novaLinha;
    }

    /**
     * Retorna se a iteração é final
     * 
     * @return boolean
     */
    private boolean isIteracaoFinal() {
        SimplexTableauLine funcaoObjetivo = iteracao.getLineFuncaoObjetivo();
        for (Map.Entry<Variavel, Double> entry : funcaoObjetivo.getCoeficientes().entrySet()) {
            if (entry.getValue() < 0) {
                return false;
            }
        }
        return true;
    }
    
}
