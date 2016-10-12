
package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.po.model.ModeloProblemaLinear;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author Nícolas Pohren
 */
public class PanelModelo extends JavaFXView<ModeloProblemaLinear>{

    /**
     * Cria um novo painel de modelo
     * 
     * @param model 
     */
    public PanelModelo(ModeloProblemaLinear model) {
        super(model);
        initGui();
    }

    /**
     * Inicializa a interface
     */
    private void initGui() {
        setCenter(buildInternalPane());
    }

    /**
     * Cria o painel interno
     * 
     * @return Node
     */
    private Node buildInternalPane() {
        VBox pane = new VBox();
        pane.getChildren().add(new PaneVariaveis(getModel().getVariaveis()));
        pane.getChildren().add(new PaneFuncaoObjetivo(getModel().getFuncaoObjetivo()));
        return pane;
    }

}
