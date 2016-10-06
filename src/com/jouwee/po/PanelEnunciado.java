package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.po.model.Enunciado;
import javafx.scene.control.Label;
import javafx.scene.web.HTMLEditor;

/**
 * Panel para exibição/edição do enunciado
 */
public class PanelEnunciado extends JavaFXView<Enunciado> {

    /**
     * Cria o panel do enunciado
     * 
     * @param model 
     */
    public PanelEnunciado(Enunciado model) {
        super(model);
        HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setHtmlText(getModel().getHtmlContent());
        Label label = new Label("Enunciado");
        setCenter(htmlEditor);
        setTop(label);
    }

}
