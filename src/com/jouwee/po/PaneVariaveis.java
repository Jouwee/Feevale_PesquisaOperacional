package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.po.model.Variaveis;

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
        
    }

}
