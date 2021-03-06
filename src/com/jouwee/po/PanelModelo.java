
package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.commons.javafx.JFX;
import static com.jouwee.commons.javafx.JFXClass.PANEL;
import com.jouwee.po.model.ModeloProblemaLinear;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author Nícolas Pohren
 */
public class PanelModelo extends JavaFXView<ModeloProblemaLinear>{

    /** Panel de variáveis */
    private PaneVariaveis panelVariaveis;
    /** Panel da função objetivo */
    private PanelFuncaoObjetivo panelFuncaoObjetivo;
    /** Panel da função objetivo */
    private PanelRestricoes panelRestricoes;
    
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
                panelFuncaoObjetivo.setModel(getModel().getFuncaoObjetivo());
                panelRestricoes.setModel(getModel());
                panelVariaveis.setModel(getModel().getVariaveis());
            }
        });
    }

    /**
     * Inicializa a interface
     */
    private void initGui() {
        setCenter(buildInternalPane());
        JFX.styleClass(this, PANEL);
    }

    /**
     * Cria o painel interno
     * 
     * @return Node
     */
    private Node buildInternalPane() {
        VBox pane = new VBox();
        pane.getChildren().add(buildPanelVariaveis());
        pane.getChildren().add(buildPanelFuncaoObjetivo());
        pane.getChildren().add(buildPanelRestricoes());
        return pane;
    }
    
    /**
     * Cria o painel de variáveis
     * 
     * @return Node
     */
    private Node buildPanelVariaveis() {
        panelVariaveis = new PaneVariaveis(getModel().getVariaveis());
        return panelVariaveis;
    }
     
    /**
     * Cria o painel da função objetivo
     * 
     * @return Node
     */
    private Node buildPanelFuncaoObjetivo() {
        panelFuncaoObjetivo = new PanelFuncaoObjetivo(getModel().getFuncaoObjetivo());
        return panelFuncaoObjetivo;
    }
    
    /**
     * Cria o painel das restrições
     * 
     * @return Node
     */
    private Node buildPanelRestricoes() {
        panelRestricoes = new PanelRestricoes(getModel());
        return panelRestricoes;
    }

}
