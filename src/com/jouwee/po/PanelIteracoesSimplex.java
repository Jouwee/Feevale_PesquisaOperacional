package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.javafx.JFX;
import com.jouwee.commons.javafx.JFXClass;
import com.jouwee.po.model.SimplexModel;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Panel de iteracoes do Simplex
 * 
 * @author Nícolas Pohren
 */
public class PanelIteracoesSimplex extends JavaFXView<SimplexModel> {

    /**
     * Cria o panel de iteração do simplex
     *
     * @param model
     */
    public PanelIteracoesSimplex(SimplexModel model) {
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
        for (int i = 0; i < getModel().getIteracoes().size(); i++) {
            panel.getChildren().add(JFX.styleClass(new Label("Iteracao " + i), JFXClass.H1));
            panel.getChildren().add(new PanelIteracaoSimplex(getModel().getIteracoes().get(i)));
        }
        return panel;
    }

}
