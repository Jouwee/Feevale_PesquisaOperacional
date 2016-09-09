package com.jouwee.po;

import com.jouwee.po.model.ResolucaoGraficaModel;
import com.jouwee.commons.application.JavaFXView;
import javafx.scene.control.Button;
import javafx.scene.web.HTMLEditor;

/**
 * Panel de resolução gráfica
 * 
 * @author Nícolas Pohren
 */
public class PanelResolucaoGrafica extends JavaFXView<ResolucaoGraficaModel> {

    /**
     * Cria o panel de resolução gráfica
     * 
     * @param model 
     */
    public PanelResolucaoGrafica(ResolucaoGraficaModel model) {
        super(model);
        
        HTMLEditor htmlEditor = new HTMLEditor();
//        htmlEditor.setPrefHeight(245);C
        htmlEditor.setHtmlText(getModel().getEnunciado().getHtmlContent());
        
        setCenter(htmlEditor);
        
    }
    
}
