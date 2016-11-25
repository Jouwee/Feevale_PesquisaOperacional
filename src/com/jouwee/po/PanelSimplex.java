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
    /** Panel do enunciado */
    private PanelEnunciado panelEnunciado;
    /** Thread do algoritmo */
    private Thread threadAlgoritmo;

    /**
     * Cria o panel do problema simplex
     *
     * @param model
     */
    public PanelSimplex(SimplexModel model) {
        super(model);
        initGui();
        addEventHandler(ModelEvent.MODEL_CHANGED, (ModelEvent event) -> {
            if (event.getTarget() == this) {
                panelAlgoritmoSimplex.setModel(getModel());
                panelModel.setModel(getModel().getModeloProblema());
                panelEnunciado.setModel(getModel().getEnunciado());
                initializeModel();
            }
        });
        initializeModel();
    }

    /**
     * Inicializa a interface
     */
    private void initGui() {
        setCenter(buildCenterPanel());
        setRight(buildPanelModelo());
    }

    /**
     * Inicializa o modelo
     */
    private void initializeModel() {
        getModel().getModeloProblema().addChildEventListener((PropertyEvent event1) -> {
            executaAlgoritmo();
        });
        executaAlgoritmo();
    }

    /**
     * Executa algoritmo
     */
    private void executaAlgoritmo() {
        threadAlgoritmo = new Thread(() -> {
            try {
                AlgoritmoSimplex algoritmo = new AlgoritmoSimplex(getModel());
                algoritmo.executa();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        threadAlgoritmo.start();
    }

    /**
     * Cria o painel central
     *
     * @return Node
     */
    private Node buildCenterPanel() {
        BorderPane pane = new BorderPane();
        pane.setTop(buildPanelEnunciado());
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

    /**
     * Retorna o painel do enunciado
     * 
     * @return Node
     */
    private Node buildPanelEnunciado() {
        panelEnunciado = new PanelEnunciado(getModel().getEnunciado());
        return panelEnunciado;
    }

}
