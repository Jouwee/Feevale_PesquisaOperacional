package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.commons.javafx.JFX;
import com.jouwee.commons.mvc.PropertyEvent;
import com.jouwee.po.model.Enunciado;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.web.HTMLEditor;

/**
 * Panel para exibição/edição do enunciado
 */
public class PanelEnunciado extends JavaFXView<Enunciado> {

    /** Editor do enunciado */
    private HTMLEditor htmlEditor;
    
    /**
     * Cria o panel do enunciado
     * 
     * @param model 
     */
    public PanelEnunciado(Enunciado model) {
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
        getModel().addChildEventListener((PropertyEvent event1) -> {
            updateEditorEnunciado();
        });
        updateEditorEnunciado();
    }

    /**
     * Inicializa a interface
     */
    private void initGui() {
        setCenter(buildEditorEnunciado());
        setTop(JFX.styleClass(new Label("Enunciado"), H2));
        JFX.styleClass(this, PANEL);
    }

    /**
     * Retorna o editor do enunciado
     * 
     * @return Node
     */
    private Node buildEditorEnunciado() {
        htmlEditor = new HTMLEditor();
        updateEditorEnunciado();
        htmlEditor.setPrefHeight(200);
        return htmlEditor;
    }
    
    /**
     * Atualiza o editor do enunciado
     */
    private void updateEditorEnunciado() {
        htmlEditor.setHtmlText(getModel().getHtmlContent());
    }

}
