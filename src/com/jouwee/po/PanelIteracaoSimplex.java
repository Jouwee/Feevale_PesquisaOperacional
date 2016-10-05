package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.po.model.SimplexTableauModel;
import javafx.scene.layout.VBox;

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
    public VBox buildPanel() {
        return new VBox();
    }

}