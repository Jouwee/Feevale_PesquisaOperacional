
package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.po.model.ModeloProblemaLinear;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author Nícolas Pohren
 */
public class PanelModelo extends JavaFXView<ModeloProblemaLinear>{

    /** Panel da função objetivo */
    private PaneFuncaoObjetivo paneFuncaoObjetivo;
    
    /**
     * Cria um novo painel de modelo
     * 
     * @param model 
     */
    public PanelModelo(ModeloProblemaLinear model) {
        super(model);
        initGui();
        addEventHandler(ModelEvent.MODEL_CHANGED, (ModelEvent event) -> {
            if (event.getTarget() == this) {
                paneFuncaoObjetivo.setModel(getModel().getFuncaoObjetivo());
            }
        });
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
        pane.getChildren().add(buildPaneFuncaoObjetivo());
        return pane;
    }
    
    /**
     * Cria o painel da função objetivo
     * 
     * @return Node
     */
    private Node buildPaneFuncaoObjetivo() {
        paneFuncaoObjetivo = new PaneFuncaoObjetivo(getModel().getFuncaoObjetivo());
        return paneFuncaoObjetivo;
    }

}
