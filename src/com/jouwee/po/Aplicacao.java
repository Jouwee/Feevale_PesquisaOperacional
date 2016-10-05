package com.jouwee.po;

import com.jouwee.commons.application.Application;
import com.jouwee.po.model.SimplexModel;
import com.jouwee.po.simplex.AlgoritmoSimplex;

/**
 * Classe principal da aplicação
 * 
 * @author Nícolas Pohren
 */
public class Aplicacao extends Application<SimplexModel> {
    
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
        super(new SimplexModel());
        
        AlgoritmoSimplex algoritmo = new AlgoritmoSimplex(getModel());
        algoritmo.executa();
        
        setBody(new PanelSimplex(getModel()));
        getActionRepository().add(new AbrirExemploAction());
    }
    
}
