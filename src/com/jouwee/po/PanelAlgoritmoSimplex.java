package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.po.model.SimplexModel;
import javafx.scene.layout.VBox;

/**
 * Panel do algoritmo Simplex
 *
 * @author Nícolas Pohren
 */
public class PanelAlgoritmoSimplex extends JavaFXView<SimplexModel> {

    /** Panel iterações simplex */
    private PanelIteracoesSimplex panelIteracoes;
    
    /**
     * Cria o panel do algoritmo simplex
     *
     * @param model
     */
    public PanelAlgoritmoSimplex(SimplexModel model) {
        super(model);
        initGui();
        addEventHandler(ModelEvent.MODEL_CHANGED, (ModelEvent event) -> {
            if (event.getTarget() == this) {
                panelIteracoes.setModel(model);
            }
        });
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
    public VBox buildPanel() {
        VBox panel = new VBox();
        panel.getChildren().add(buildPanelIteracoesSimplex());
        return panel;
    }
    
    /**
     * Cria o painel de iterações
     * 
     * @return JavaFXView
     */
    private JavaFXView buildPanelIteracoesSimplex() {
        panelIteracoes = new PanelIteracoesSimplex(getModel());
        return panelIteracoes;
    }

}
