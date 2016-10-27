
package com.jouwee.po;

import com.google.gson.GsonBuilder;
import com.jouwee.commons.application.Action;
import com.jouwee.commons.application.ActionEvent;
import com.jouwee.commons.math.EquationParser;
import com.jouwee.commons.math.ExpressionParser;
import com.jouwee.commons.math.ParsingException;
import com.jouwee.po.model.FuncaoObjetivo;
import com.jouwee.po.model.Objetivo;
import com.jouwee.po.model.Restricao;
import com.jouwee.po.model.SimplexModel;
import com.jouwee.po.model.Variavel;

/**
 *
 * @author Nícolas Pohren
 */
public class SalvarAction extends Action {

    /**
     * Cria uma ação de abrir exemplo
     */
    public SalvarAction() {
        super("Salvar");
    }

    @Override
    public void performed(ActionEvent event) {
        System.out.println(new GsonBuilder().create().toJson(Aplicacao.get().getModel()));
    }

}
