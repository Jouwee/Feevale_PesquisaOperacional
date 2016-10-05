package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.po.model.SimplexModel;
import javafx.scene.layout.VBox;

/**
 * Panel do algoritmo Simplex
 *
 * @author Nícolas Pohren
 */
public class PanelAlgoritmoSimplex extends JavaFXView<SimplexModel> {

    /**
     * Cria o panel do algoritmo simplex
     *
     * @param model
     */
    public PanelAlgoritmoSimplex(SimplexModel model) {
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
    public VBox buildPanel() {
        VBox panel = new VBox();
        panel.getChildren().add(new PanelIteracoesSimplex(getModel()));
        return panel;
    }

}
