package com.jouwee.po;

import com.jouwee.po.model.ResolucaoGraficaModel;
import com.jouwee.commons.application.JavaFXView;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * Panel de resolução gráfica
 * 
 * @author Nícolas Pohren
 */
public class PanelResolucaoGrafica extends JavaFXView<ResolucaoGraficaModel> {

    /**
     * Cria o panel de resolução gráfica
     * 
     * @param model 
     */
    public PanelResolucaoGrafica(ResolucaoGraficaModel model) {
        super(model);
        setTop(new PanelEnunciado(model.getEnunciado()));
        setCenter(buildCenterPanel());
    }

    /**
     * Cria o painel central
     * 
     * @return Node
     */
    private Node buildCenterPanel() {
        BorderPane pane = new BorderPane();
        pane.setRight(new PanelModelo(getModel().getModeloProblema()));
        pane.setCenter(new PanelGrafico(getModel()));
        return pane;
    }
    
}
