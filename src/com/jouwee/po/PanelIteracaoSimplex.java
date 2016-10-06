package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.po.model.SimplexTableauModel;
import com.jouwee.po.model.Variavel;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Panel de uma iteração
 * 
 * @author Nícolas Pohren
 */
public class PanelIteracaoSimplex extends JavaFXView<SimplexTableauModel> {

    /**
     * Cria o panel de iteração do simplex
     *
     * @param model
     */
    public PanelIteracaoSimplex(SimplexTableauModel model) {
        super(model);
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
        gridPane.addRow(0, buildHeaderRow());
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
            headerNodes.add(new Label(variavel.getName()));
        }
        headerNodes.add(new Label("Linha"));
        headerNodes.add(new Label("Divisão"));
        return headerNodes.toArray(new Node[]{});
    }

}