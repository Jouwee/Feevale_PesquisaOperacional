package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.po.model.SimplexTableauLine;
import com.jouwee.po.model.SimplexTableauModel;
import com.jouwee.po.model.Variavel;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Panel de uma iteração
 * 
 * @author Nícolas Pohren
 */
public class PanelIteracaoSimplex extends JavaFXView<SimplexTableauModel> {

    private final NumberFormat formatter;
    
    /**
     * Cria o panel de iteração do simplex
     *
     * @param model
     */
    public PanelIteracaoSimplex(SimplexTableauModel model) {
        super(model);
        formatter = new DecimalFormat("#0.00");
        initGui();
    }
    
    /**
     * Inicializa a interface
     */
    public void initGui() {
        setCenter(buildPanel());
    }
    
    /**
     * Cria o painel principal
     * 
     * @return 
     */
    public GridPane buildPanel() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
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
        headerNodes.add(new Label("Pivô"));
        headerNodes.add(new Label("Base"));
        headerNodes.add(new Label("Valor"));
        for (Variavel variavel : getModel().getVariables()) {
            Label label = new Label(variavel.getName());
            if (variavel.equals(getModel().getEntraNaBase())) {
                label.setTextFill(Color.BLUE);
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
        headerNodes.add(new Label(""));
        Label label = new Label(simplexLine.getVariavel().getName());
        if (simplexLine.getVariavel().equals(getModel().getSaiDaBase())) {
            label.setTextFill(Color.RED);
        }
        headerNodes.add(label);
        headerNodes.add(new Label(String.valueOf(simplexLine.getValor())));
        for (Variavel variavel : getModel().getVariables()) {
            headerNodes.add(new Label(formatter.format(simplexLine.getCoeficiente(variavel))));
        }
        headerNodes.add(new Label(String.valueOf(line - 1)));
        headerNodes.add(new Label(montaLabelDivisao(simplexLine)));
        return headerNodes.toArray(new Node[]{});
    }

    /**
     * Monta o label de divisão
     * 
     * @param simplexLine
     * @return String
     */
    private String montaLabelDivisao(SimplexTableauLine simplexLine) {
        double valor = simplexLine.getValor();
        double coeficienteEntraNaBase = simplexLine.getCoeficiente(getModel().getEntraNaBase());
        String sDivisao = String.valueOf(simplexLine.getDivisao(getModel().getEntraNaBase()));
        return String.valueOf(valor) + " / " + String.valueOf(coeficienteEntraNaBase) + " = " + sDivisao;
    }

}