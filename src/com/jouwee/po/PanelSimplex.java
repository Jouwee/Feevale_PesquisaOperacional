
package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.po.model.SimplexModel;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * Panel do problema Simplex
 * 
 * @author Nícolas Pohren
 */
public class PanelSimplex extends JavaFXView<SimplexModel> {

    /**
     * Cria o panel do problema simplex
     * 
     * @param model 
     */
    public PanelSimplex(SimplexModel model) {
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
        pane.setCenter(new PanelAlgoritmoSimplex(getModel()));
        return pane;
    }
    
}
