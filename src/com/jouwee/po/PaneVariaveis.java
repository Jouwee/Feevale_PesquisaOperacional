package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.javafx.JFX;
import static com.jouwee.commons.javafx.JFXClass.H2;
import com.jouwee.po.model.Variaveis;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Painel de variáveis
 * 
 * @author Nícolas Pohren
 */
class PaneVariaveis extends JavaFXView<Variaveis> {

    /**
     * Cria o painel de variáveis
     * 
     * @param variaveis 
     */
    public PaneVariaveis(Variaveis variaveis) {
        super(variaveis);
        initGui();
    }

    /**
     * Inicializa a interface
     */
    private void initGui() {
        setTop(JFX.styleClass(new Label("Função objetivo"), H2));
        setCenter(buildVariaveisPane());
    }

    /**
     * Cria o panel interno
     * 
     * @return Node
     */
    private Node buildVariaveisPane() {
        GridPane panel = new GridPane();
        
        return panel;
    }

}
