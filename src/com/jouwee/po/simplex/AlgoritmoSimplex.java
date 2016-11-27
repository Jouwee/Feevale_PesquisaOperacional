package com.jouwee.po.simplex;

import com.jouwee.commons.math.AbsoluteValueNode;
import com.jouwee.commons.math.EquationType;
import com.jouwee.commons.math.Expression;
import com.jouwee.po.model.Restricao;
import com.jouwee.po.model.SimplexModel;
import com.jouwee.po.model.SimplexTableauLine;
import com.jouwee.po.model.SimplexTableauModel;
import com.jouwee.po.model.Variavel;
import java.util.ArrayList;
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
        model.clearIteracoes();
        if (!valida()) {
            return;
        }
        model.setErroValidacao(null);
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
     * Valida o modelo
     * 
     * @return boolean
     */
    private boolean valida() {
        if (!validaRestricoes()) {
            return false;
        }
        return true;
    }
    
    /**
     * Valida as restrições
     * 
     * @return boolean
     */
    private boolean validaRestricoes() {
        for (Restricao restricao : model.getModeloProblema().getRestricoes()) {
            if (!validaRestricao(restricao)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Valida uma restrição
     * 
     * @param restricao
     * @return boolean
     */
    private boolean validaRestricao(Restricao restricao) {
        if (restricao.isNaoNegatividade()) {
            return true;
        }
        if (restricao.getEquacao().getType() == EquationType.EQUALS_TO) {
            model.setErroValidacao("Tipo de restricao '=' não suportado! (%1$s)", restricao.getDescricao());
            return false;
        }
        if (restricao.getEquacao().getType() == EquationType.GREATER_THAN_OR_EQUALS_TO) {
            model.setErroValidacao("Tipo de restricao '>=' não suportado! (%1$s)", restricao.getDescricao());
            return false;
        }
        if (restricao.getEquacao().getType() != EquationType.LESSER_THAN_OR_EQUALS_TO) {
            model.setErroValidacao("Tipo de restricao não suportado! (%1$s)", restricao.getDescricao());
            return false;
        }
        return true;
    }

    /**
     * Normaliza o modelo
     */
    private void normalizaModelo() {
        iteracao = new SimplexTableauModel(null, normalizaVariaveis());
        iteracao.addLine(normalizaFuncaoObjetivo(iteracao));        
        for (Restricao restricao : model.getModeloProblema().getRestricoes()) {
            if (!restricao.isNaoNegatividade()) {
                iteracao.addLine(normalizaRestricao(restricao));
            }
        }
        model.addIteracao(iteracao);
    }

    /**
     * Normaliza as variáveis
     * 
     * @return {@code List<Variavel>}
     */
    private List<Variavel> normalizaVariaveis() {
        List<Variavel> variaveis = new ArrayList<>();
        variaveis.add(new Variavel("x0", "Função objetivo", true));
        for (Variavel variavel : model.getModeloProblema().getVariaveis().getVariaveis()) {
            variaveis.add(variavel);
        }
        int i = 1;
        for (Restricao restricao : model.getModeloProblema().getRestricoes()) {
            if (!restricao.isNaoNegatividade()) {
                variaveis.add(new Variavel("x" + i, "Folga para restrição \"" + restricao.getDescricao() + "\"", restricao));
                i++;
            }
        }
        return variaveis;
    }
    
    /**
     * Normaliza a função objetivo
     * 
     * @return SimplexTableauLine
     */
    private SimplexTableauLine normalizaFuncaoObjetivo(SimplexTableauModel iteracao) {
        Expression funcaoObjetivo = model.getModeloProblema().getFuncaoObjetivo().getEquacao();
        SimplexTableauLine line = new SimplexTableauLine();
        line.setVariavel(iteracao.getVariavel("x0"));
        line.setValor(0);
        line.setCoeficiente(iteracao.getVariavel("x0"), 1);
        for (Variavel variavel : model.getModeloProblema().getVariaveis().getVariaveis()) {
            line.setCoeficiente(variavel, -funcaoObjetivo.getVariableCoeficient(variavel.getName()));
        }
        line.setCoeficiente(iteracao.getVariavel("x1"), 0);
        line.setCoeficiente(iteracao.getVariavel("x2"), 0);
        return line;
    }

    /**
     * Identifica a variável que entra na base
     */
    private void identificaVariavelQueEntraNaBase() {
        SimplexTableauLine funcaoObjetivo = iteracao.getLines().get(0);
        Variavel variavelMenorCoeficiente = iteracao.getVariaveis().get(0);
        for (Variavel variavel : iteracao.getVariaveis()) {
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
        SimplexTableauModel novaIteracao = new SimplexTableauModel(iteracaoAnterior, iteracaoAnterior.getVariaveis());
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
        return iteracao.isIteracaoFinal();
    }

    /**
     * Normaliza uma restrição
     * 
     * @param restricao
     * @return SimplexTableauLine
     */
    private SimplexTableauLine normalizaRestricao(Restricao restricao) {
        SimplexTableauLine line = new SimplexTableauLine();
        Variavel variavelRestricao = iteracao.getVariavel(restricao);
        line.setVariavel(variavelRestricao);
        line.setValor(((AbsoluteValueNode)restricao.getEquacao().getRightFunction()).getValue());
        line.setCoeficiente(iteracao.getVariavelFuncaoObjetivo(), 0);
        for (Variavel variavel : model.getModeloProblema().getVariaveis().getVariaveis()) {
            line.setCoeficiente(variavel, restricao.getEquacao().getLeftFunction().getVariableCoeficient(variavel.getName()));
        }
        for (Variavel variavelAuxiliar : iteracao.getVariaveisAuxiliares()) {
            if (variavelAuxiliar.equals(variavelRestricao)) {
                line.setCoeficiente(variavelAuxiliar, 1);
            } else {
                line.setCoeficiente(variavelAuxiliar, 0);
            }
        }
        return line;
    }

}
