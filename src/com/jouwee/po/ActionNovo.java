package com.jouwee.po;

import com.jouwee.commons.application.Action;
import com.jouwee.commons.application.ActionEvent;
import com.jouwee.po.model.SimplexModel;

/**
 * Ação para abrir um exemplo
 *
 * @author Nícolas Pohren
 */
public class ActionNovo extends Action {

    /**
     * Cria uma ação de abrir exemplo
     */
    public ActionNovo() {
        super("Novo");
    }

    @Override
    public void performed(ActionEvent event) {
        SimplexModel model = new SimplexModel();
        Aplicacao.get().setModel(model);
    }

}
