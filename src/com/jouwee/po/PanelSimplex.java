
package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.po.model.SimplexModel;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * Panel do problema Simplex
 * 
 * @author Nícolas Pohren
 */
public class PanelSimplex extends JavaFXView<SimplexModel> {

    /** Panel do algoritmo simplex */
    private PanelAlgoritmoSimplex panelAlgoritmoSimplex;
    /** Panel do modelo */
    private PanelModelo panelModel;
    
    /**
     * Cria o panel do problema simplex
     * 
     * @param model 
     */
    public PanelSimplex(SimplexModel model) {
        super(model);
//        setTop(new PanelEnunciado(model.getEnunciado()));
        setCenter(buildCenterPanel());
        addEventHandler(ModelEvent.MODEL_CHANGED, (ModelEvent event) -> {
            if (event.getTarget() == this) {
                panelAlgoritmoSimplex.setModel(getModel());
                panelModel.setModel(getModel().getModeloProblema());
            }
        });
    }

    /**
     * Cria o painel central
     * 
     * @return Node
     */
    private Node buildCenterPanel() {
        BorderPane pane = new BorderPane();
        pane.setRight(buildPanelModelo());
        pane.setCenter(buildPanelAlgoritmoSimplex());
        return pane;
    }
    
    /**
     * Cria o panel do algoritmo simplex
     * 
     * @return Node
     */
    private Node buildPanelAlgoritmoSimplex() {
        panelAlgoritmoSimplex = new PanelAlgoritmoSimplex(getModel());
        return panelAlgoritmoSimplex;
    }

    /**
     * Cria o panel do modelo
     * 
     * @return Node
     */
    private Node buildPanelModelo() {
        panelModel = new PanelModelo(getModel().getModeloProblema());
        return panelModel;
    }
    
}
