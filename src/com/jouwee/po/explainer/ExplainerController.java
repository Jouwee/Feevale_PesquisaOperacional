package com.jouwee.po.explainer;

import com.google.gson.GsonBuilder;
import com.jouwee.commons.math.Equation;
import com.jouwee.po.model.SimplexModel;
import com.jouwee.po.model.SimplexTableauLine;
import com.jouwee.po.model.SimplexTableauModel;
import com.jouwee.po.model.Variavel;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller de explicações
 * 
 * @author Nícolas Pohren
 */
public class ExplainerController {

    /** Painél de explicações */
    private final ExplainPanel panel;
    /** Explicações */
    private Explicacoes explicacoes;

    /**
     * Cria o controller de explicações
     * 
     * @param panel 
     */
    public ExplainerController(ExplainPanel panel) {
        this.panel = panel;
    }
    
    /**
     * Explica o valor da iteração 0
     * 
     * @param variavelBase
     */
    public void explainValorIteracao0(Variavel variavelBase) {
        loadExplicacao("valorIteracao0", new String[][] {
            {"variavelBase", variavelBase.getName()},
            {"origem", variavelBase.getRestricaoFolga() == null ? "função objetivo" : "restricao \"" + variavelBase.getRestricaoFolga().getDescricao() + '"'},
            {"equacao", highlight(getModel().getEtapaNormalizacao().getLine(variavelBase).getEquation(), null, 1) },
        });
        showExplainPanel();
    }
    
    /**
     * Explica um coeficiente da iteração 0
     * 
     * @param variavelBase
     * @param variavel 
     */
    public void explainCoeficienteIteracao0(Variavel variavelBase, Variavel variavel) {
        loadExplicacao("coeficienteIteracao0", new String[][] {
            {"variavelBase", variavelBase.getName()},
            {"origem", variavelBase.getRestricaoFolga() == null ? "função objetivo" : "restrição \"" + variavelBase.getRestricaoFolga().getDescricao() + '"'},
            {"equacao", highlight(getModel().getEtapaNormalizacao().getLine(variavelBase).getEquation(), variavel, 1) },
        });
        showExplainPanel();
    }
    
    /**
     * Explica uma variável da iteração 0
     * 
     * @param variavelBase
     */
    public void explainVariavelIteracao0(Variavel variavelBase) {
        loadExplicacao("variavelIteracao0", new String[][] {
            {"variavelBase", variavelBase.getName()},
            {"origem", variavelBase.getRestricaoFolga() == null ? "função objetivo" : "restrição \"" + variavelBase.getRestricaoFolga().getDescricao() + '"'},
        });
        showExplainPanel();
    }
    
    /**
     * Explica o resultado das divisões
     * 
     * @param simplexLine
     * @param model
     */
    public void explainResultadoDivisao(SimplexTableauModel model, SimplexTableauLine simplexLine) {
        double valor = simplexLine.getValor();
        double coeficienteEntraNaBase = simplexLine.getCoeficiente(model.getEntraNaBase());
        String sDivisao = simplexLine.getDivisaoFormatado(model.getEntraNaBase());
        String equacao = String.valueOf(valor) + " / " + String.valueOf(coeficienteEntraNaBase) + " = " + sDivisao;
        loadExplicacao("resultadoDivisao", new String[][] {
            {"equacao", equacao},
            {"entraNaBase", model.getEntraNaBase() == null ? "" : model.getEntraNaBase().getName()},
        });
        showExplainPanel();
    }
    
    /**
     * Explica porque uma variavel entra na base
     * 
     * @param variavel
     * @param coeficiente
     */
    public void explainEntraNaBase(Variavel variavel, double coeficiente) {
        loadExplicacao("entraNaBase", new String[][] {
            {"variavel", variavel.getName()},
            {"coeficiente", String.valueOf(coeficiente)},
        });
        showExplainPanel();
    }
    
    /**
     * Exibe o painél de explicações
     */
    private void showExplainPanel() {
        panel.setVisible(true);
    }

    /**
     * Carrega uma explicação à partir do JSON
     * 
     * @param key 
     */
    private void loadExplicacao(String key, String[][] keyValuePairs) {
        Map<String, String> pairs = normalize(keyValuePairs);
        Explicacao explicacao = getExplicacoes().get(key);
        panel.setExplicacao(process(explicacao.getTexto(), pairs));
    }

    /**
     * Retorna as explicações
     * 
     * @return {@code Map<String, Explicacao>}
     */
    private Map<String, Explicacao> getExplicacoes() {
        if (explicacoes == null) {
            Reader reader = new InputStreamReader(ExplainerController.class.getResourceAsStream("Explicacoes.json"));
            explicacoes = new GsonBuilder().create().fromJson(reader, Explicacoes.class);
        }
        return explicacoes.getExplicacoes();
    }

    /**
     * Normaliza o chave/valor
     * 
     * @param keyValuePairs
     * @return 
     */
    private Map<String, String> normalize(String[][] keyValuePairs) {
        Map<String, String> map = new HashMap<>();
        for (String[] keyValuePair : keyValuePairs) {
            map.put(keyValuePair[0], keyValuePair[1]);
        }
        return map;
    }

    /**
     * Processa o texto
     * 
     * @param texto
     * @param pairs
     * @return String
     */
    private String process(String texto, Map<String, String> pairs) {
        for (Map.Entry<String, String> entry : pairs.entrySet()) {
            texto = texto.replace("${" + entry.getKey() + '}', entry.getValue());
        }
        return texto;
    }
    
    /**
     * Retorna o modelo do simplex
     * 
     * @return SimplexModel
     */
    private SimplexModel getModel() {
        return panel.getModel();
    }

    /**
     * Destaca uma variável na equação. Se passado {@code null}, retorna o valor absoluto (Direita)
     * 
     * @param equation
     * @param variavelBase
     * @return String
     */
    private String highlight(Equation equation, Variavel variavelBase, int highLight) {
        if (variavelBase == null) {
            String string = equation.toString();
            int indexOfEquals = string.indexOf("=") + 1;
            return string.substring(0, indexOfEquals) + "<span class=\"highlight" + highLight + "\">" + string.substring(indexOfEquals) + "<span>";
        } else {
            // TODO:
            return equation.toString();
        }
    }
    
    /**
     * Explicações
     */
    private class Explicacoes {
    
        /** Explicacoes */
        private Map<String, Explicacao> explicacoes;

        public Map<String, Explicacao> getExplicacoes() {
            return explicacoes;
        }

        public void setExplicacoes(Map<String, Explicacao> explicacoes) {
            this.explicacoes = explicacoes;
        }
        
    }
    
}
