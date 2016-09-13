package com.jouwee.po;

import com.jouwee.po.model.ResolucaoGraficaModel;
import com.jouwee.commons.application.Application;

/**
 * Classe principal da aplicação
 * 
 * @author Nícolas Pohren
 */
public class Aplicacao extends Application<ResolucaoGraficaModel> {
    
    /**
     * Cria a aplicação
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Cria a aplicação
     */
    public Aplicacao() {
        super(new ResolucaoGraficaModel());
        setBody(new PanelResolucaoGrafica(getModel()));
        getActionRepository().add(new AbrirExemploAction());
    }
    
}
