package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.commons.mvc.PropertyEvent;
import com.jouwee.po.model.SimplexModel;
import com.jouwee.po.simplex.AlgoritmoSimplex;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * Panel do problema Simplex
 *
 * @author Nícolas Pohren
 */
public class PanelSimplex extends JavaFXView<SimplexModel> {

    /** Panel do modelo */
    private PanelModelo panelModel;
    /** Panel do algoritmo simplex */
    private PanelAlgoritmoSimplex panelAlgoritmoSimplex;

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
                initializeModel();
            }
        });
        initializeModel();
    }

    /**
     * Inicializa o modelo
     */
    private void initializeModel() {
        getModel().addChildEventListener((PropertyEvent event1) -> {
            executaAlgoritmo();
        });
        executaAlgoritmo();
    }

    /**
     * Executa algoritmo
     */
    private void executaAlgoritmo() {
        AlgoritmoSimplex algoritmo = new AlgoritmoSimplex(getModel());
        algoritmo.executa();
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
