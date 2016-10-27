package com.jouwee.po;

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
 * Ação para abrir um exemplo
 *
 * @author Nícolas Pohren
 */
public class AbrirExemploAction extends Action {

    /**
     * Cria uma ação de abrir exemplo
     */
    public AbrirExemploAction() {
        super("Abrir exemplo");
    }

    @Override
    public void performed(ActionEvent event) {
        try {
            ExpressionParser expressionParser = new ExpressionParser();
            EquationParser equationParser = new EquationParser();
            Variavel a = new Variavel("a", "kg de SWEET");
            Variavel b = new Variavel("b", "kg de LO-SUGAR");
            SimplexModel model = new SimplexModel();
            model.getModeloProblema().getVariaveis().add(a);
            model.getModeloProblema().getVariaveis().add(b);
            model.getModeloProblema().setFuncaoObjetivo(new FuncaoObjetivo(Objetivo.MAXIMIZAR, expressionParser.parse("0.20 * a + 0.30 * b")));
            model.getModeloProblema().addRestricao(new Restricao("Sacarina", equationParser.parse("0.2 * a + 0.4 * b <= 14")));
            model.getModeloProblema().addRestricao(new Restricao("Dextrose", equationParser.parse("0.4 * a + 0.3 * b <= 18")));
            model.getModeloProblema().addRestricao(new Restricao("Não negatividade a", equationParser.parse("a >= 0")));
            model.getModeloProblema().addRestricao(new Restricao("Não negatividade b", equationParser.parse("b >= 0")));
            model.getEnunciado().setHtmlContent("<b>Problema de Mistura</b>: Um fabricante de adoçante artificial mistura 14 kg de sacarina e 18 kg de dextrose para preparar 2 novos produtos: SWEET e LO-SUGAR. Cada kg de SWEET contém 0,4 kg de dextrose e 0,2 kg de sacarina, enquanto que cada kg de LO-SUGAR contém 0,3 kg de dextrose e 0,4 kg de sacarina. Se o lucro por kg de SWEET é $0,20 e de LO-SUGAR é $0,30, quantos kg de cada devem ser feitos para maximizar o lucro?");
            Aplicacao.get().setModel(model);
        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }

}
