package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.javafx.JFX;
import com.jouwee.commons.javafx.control.TablePane;
import com.jouwee.po.model.SimplexTableauLine;
import com.jouwee.po.model.SimplexTableauModel;
import com.jouwee.po.model.Variavel;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Panel de uma iteração
 * 
 * @author Nícolas Pohren
 */
public class PanelIteracaoSimplex extends JavaFXView<SimplexTableauModel> {

    /** Formatter */
    private final NumberFormat formatter;
    /** Iteração */
    private final int iteracao;
    
    /**
     * Cria o panel de iteração do simplex
     *
     * @param model
     */
    public PanelIteracaoSimplex(SimplexTableauModel model, int iteracao) {
        super(model);
        formatter = new DecimalFormat("#0.00");
        this.iteracao = iteracao;
        initGui();
    }
    
    /**
     * Inicializa a interface
     */
    public void initGui() {
        getStylesheets().add(PanelIteracaoSimplex.class.getResource("PanelIteracaoSimplex.css").toExternalForm());
        setCenter(buildPanel());
    }
    
    /**
     * Cria o painel principal
     * 
     * @return 
     */
    public GridPane buildPanel() {
        GridPane gridPane = new TablePane();
        gridPane.addRow(0, buildHeaderRow());
        int line = 0;
        for (SimplexTableauLine simplexLine : getModel().getLines()) {
            gridPane.addRow(++line, buildValuesRow(line, simplexLine));
        }
        return gridPane;
    }

    /**
     * Cria o cabeçalho
     * 
     * @return Node[]
     */
    private Node[] buildHeaderRow() {
        List<Node> headerNodes = new ArrayList<>();
        headerNodes.add(new Label("Base"));
        headerNodes.add(new Label("Valor"));
        for (Variavel variavel : getModel().getVariaveis()) {
            Label label;
            if (variavel.equals(getModel().getEntraNaBase())) {
                label = buildExplicacao(variavel.getName(), () -> {
                    Aplicacao.get().getExplainerController().explainEntraNaBase(variavel, getModel().getLineFuncaoObjetivo().getCoeficiente(variavel));
                });
                label.getStyleClass().add("entraNaBase");
            } else {
                label = new Label(variavel.getName());
            }
            headerNodes.add(label);
        }
        headerNodes.add(new Label("Linha"));
        headerNodes.add(new Label("Divisão"));
        return headerNodes.toArray(new Node[]{});
    }

    /**
     * Cria a linha para uma linha da tabela
     * 
     * @param simplexLine
     * @return Node[]
     */
    private Node[] buildValuesRow(int line, SimplexTableauLine simplexLine) {
        List<Node> headerNodes = new ArrayList<>();
        Variavel variavelBase = simplexLine.getVariavel();
        Label label = buildExplicacao(variavelBase.getName(), () -> {
            if (iteracao == 0) {
                Aplicacao.get().getExplainerController().explainVariavelIteracao0(variavelBase);
            } else {
                Aplicacao.get().getExplainerController().explainVariavel(variavelBase);
            }
        });
        if (variavelBase.equals(getModel().getSaiDaBase())) {
            label = buildExplicacao(variavelBase.getName(), () -> {
                Aplicacao.get().getExplainerController().explainSaiDaBase(variavelBase);
            });
            label.setTextFill(Color.RED);
        }
        headerNodes.add(label);
        headerNodes.add(buildLabelValor(simplexLine, variavelBase));
        for (Variavel variavel : getModel().getVariaveis()) {
            headerNodes.add(buildLabelCoeficiente(simplexLine, variavel, variavelBase));
        }
        headerNodes.add(new Label(String.valueOf(line - 1)));
        headerNodes.add(buildExplicacao(montaLabelDivisao(simplexLine), () -> {
            Aplicacao.get().getExplainerController().explainResultadoDivisao(getModel(), simplexLine);
        }));
        return headerNodes.toArray(new Node[]{});
    }

    /**
     * Cria o label de valor
     * 
     * @param simplexLine
     * @param variavelBase 
     */
    private Label buildLabelValor(SimplexTableauLine simplexLine, Variavel variavelBase) {
        if (iteracao == 0) {
            return buildExplicacao(formatter.format(simplexLine.getValor()), () -> {
                Aplicacao.get().getExplainerController().explainValorIteracao0(variavelBase);
            });
        } else {
            if (simplexLine.getVariavel().equals(getModel().getIteracaoAnterior().getEntraNaBase())) {
                return buildExplicacao(formatter.format(simplexLine.getValor()), () -> {
                    Aplicacao.get().getExplainerController().explainLinhaEntrouBase(getModel().getIteracaoAnterior(), getModel());
                });
            } else {
                return buildExplicacao(formatter.format(simplexLine.getValor()), () -> {
                    Aplicacao.get().getExplainerController().explainOutraLinha(getModel().getIteracaoAnterior(), getModel(), simplexLine.getVariavel());
                });
            }
        }
    }
    
    /**
     * Cria o label de coeficiente
     * 
     * @param simplexLine
     * @param variavel
     * @param variavelBase
     * @return Label    
     */
    private Label buildLabelCoeficiente( SimplexTableauLine simplexLine, Variavel variavel, Variavel variavelBase) {
        if (iteracao == 0) {
            return buildExplicacao(formatter.format(simplexLine.getCoeficiente(variavel)), () -> {
            Aplicacao.get().getExplainerController().explainCoeficienteIteracao0(variavelBase, variavel);
        });
        } else {
            if (simplexLine.getVariavel().equals(getModel().getIteracaoAnterior().getEntraNaBase())) {
                return buildExplicacao(formatter.format(simplexLine.getCoeficiente(variavel)), () -> {
                    Aplicacao.get().getExplainerController().explainLinhaEntrouBase(getModel().getIteracaoAnterior(), getModel());
                });
            } else {
                return buildExplicacao(formatter.format(simplexLine.getCoeficiente(variavel)), () -> {
                    Aplicacao.get().getExplainerController().explainOutraLinha(getModel().getIteracaoAnterior(), getModel(), simplexLine.getVariavel());
                });
            }
        } 
    }
    
    /**
     * Cria um label de explicação
     * 
     * @param text
     * @param abreExplicacao
     * @return 
     */
    private Label buildExplicacao(String text, Runnable abreExplicacao) {
        Label label = new Label(text);
        label.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            abreExplicacao.run();
        });
        return JFX.styleClass(label, CLICKABLE);
    }

    /**
     * Monta o label de divisão
     * 
     * @param simplexLine
     * @return String
     */
    private String montaLabelDivisao(SimplexTableauLine simplexLine) {
        return simplexLine.getDivisaoFormatado(getModel().getEntraNaBase());
    }

}