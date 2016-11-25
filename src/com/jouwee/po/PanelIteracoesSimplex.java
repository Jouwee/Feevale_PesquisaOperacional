package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.commons.javafx.JFX;
import com.jouwee.commons.javafx.JFXClass;
import com.jouwee.po.model.SimplexModel;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Panel de iteracoes do Simplex
 * 
 * @author Nícolas Pohren
 */
public class PanelIteracoesSimplex extends JavaFXView<SimplexModel> {

    /** Panel de iterações */
    private VBox panel;
    
    /**
     * Cria o panel de iteração do simplex
     *
     * @param model
     */
    public PanelIteracoesSimplex(SimplexModel model) {
        super(model);
        initGui();
        addEventHandler(ModelEvent.MODEL_CHANGED, (ModelEvent event) -> {
            if (event.getTarget() == this) {
                initializeModel();
            }
        });
        initializeModel();
    }
    
    /**
     * Inicializa o modelo
     */
    private void initializeModel() {
        getModel().addListenerIteracoes((Observable observable) -> {
            updatePanel();
        });
        updatePanel();
    }
        
    
    /**
     * Inicializa a interface
     */
    private void initGui() {
        setCenter(buildPanel());
    }
    
    /**
     * Cria o painel principal
     * 
     * @return 
     */
    public VBox buildPanel() {
        panel = new VBox();
        return panel;
    }

    /**
     * Update the panel
     */
    public void updatePanel() {
        Platform.runLater(() -> {
            panel.getChildren().clear();
            for (int i = 0; i < getModel().getIteracoes().size(); i++) {
                panel.getChildren().add(JFX.styleClass(new Label("Iteração " + i), JFXClass.H2));
                panel.getChildren().add(new PanelIteracaoSimplex(getModel().getIteracoes().get(i), i));
            }
        });
    }

}
