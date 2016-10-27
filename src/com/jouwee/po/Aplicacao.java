package com.jouwee.po;

import com.jouwee.commons.application.Application;
import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.po.model.SimplexModel;
import com.jouwee.po.simplex.AlgoritmoSimplex;

/**
 * Classe principal da aplicação
 * 
 * @author Nícolas Pohren
 */
public class Aplicacao extends Application<SimplexModel> {
    
    /** Instância da aplicação */
    private static Aplicacao instance;
    /** Panel do simplex */
    private PanelSimplex simplexPanel;
    
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

        initGui();
        instance = this;
    }

    /**
     * Retorna a aplicação
     * 
     * @return Aplicacao
     */
    public static Aplicacao get() {
        return instance;
    }
    
    /**
     * Inicializa a interface
     */
    private void initGui() {
        setIcon(Aplicacao.class.getResourceAsStream("/com/jouwee/po/Icone32x32.PNG"));
        setBody(buildSimplexPanel());
        getActionRepository().add(new ActionNovo());
        getActionRepository().add(new SalvarAction());
        getActionRepository().add(new AbrirExemploAction());
    }

    @Override
    public void onStart() {
        addEventHandler(ModelEvent.MODEL_CHANGED, (ModelEvent event) -> {
            simplexPanel.setModel((SimplexModel) event.getNewModel());
        });
    }

    /**
     * Cria o panel do Simplex
     * 
     * @return JavaFXView
     */
    private JavaFXView buildSimplexPanel() {
        simplexPanel = new PanelSimplex(getModel());
        return simplexPanel;
    }

}
