package com.jouwee.po.simplex;

import com.jouwee.commons.math.EquationParser;
import com.jouwee.commons.math.ExpressionParser;
import static com.jouwee.commons.test.CollectionsAssert.*;
import com.jouwee.po.model.FuncaoObjetivo;
import com.jouwee.po.model.Objetivo;
import com.jouwee.po.model.Restricao;
import com.jouwee.po.model.SimplexModel;
import com.jouwee.po.model.SimplexTableauLine;
import com.jouwee.po.model.Variavel;
import java.util.AbstractMap;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testes do algoritmo Simplex
 *
 * @author Nícolas Pohren
 */
public class AlgoritmoSimplexTest {

    /** Parser de expressões */
    private ExpressionParser expressionParser;
    /** Parser de equações */
    private EquationParser equationParser;

    @Before
    public void setup() {
        expressionParser = new ExpressionParser();
        equationParser = new EquationParser();
    }

    @Test
    public void testCase1() throws Exception {

        Variavel x0 = new Variavel("x0", "");
        Variavel a = new Variavel("a", "");
        Variavel b = new Variavel("b", "");
        Variavel x1 = new Variavel("x1", "");
        Variavel x2 = new Variavel("x2", "");

        SimplexModel model = new SimplexModel();
        model.getModeloProblema().getVariaveis().add(a);
        model.getModeloProblema().getVariaveis().add(b);
        model.getModeloProblema().setFuncaoObjetivo(new FuncaoObjetivo(Objetivo.MAXIMIZAR, expressionParser.parse("0.20 * a + 0.30 * b")));
        model.getModeloProblema().addRestricao(new Restricao("", equationParser.parse("0.2 * a + 0.4 * b <= 14")));
        model.getModeloProblema().addRestricao(new Restricao("", equationParser.parse("0.4 * a + 0.3 * b <= 18")));
        model.getModeloProblema().addRestricao(new Restricao("", equationParser.parse("a >= 0")));
        model.getModeloProblema().addRestricao(new Restricao("", equationParser.parse("b >= 0")));

        AlgoritmoSimplex algoritmo = new AlgoritmoSimplex(model);
        algoritmo.executa();

        // Iteração 0 (Normalização)
        assertEquals(new Variavel[]{x0, a, b, x1, x2}, model.getIteracoes().get(0).getVariables());
        assertEquals(new SimplexTableauLine[]{
            new SimplexTableauLine(x0, 0, entry(x0, 1), entry(a, -0.2), entry(b, -0.3), entry(x1, 0), entry(x2, 0)),
            new SimplexTableauLine(x1, 14, entry(x0, 0), entry(a, 0.2), entry(b, 0.4), entry(x1, 1), entry(x2, 0)),
            new SimplexTableauLine(x2, 18, entry(x0, 0), entry(a, 0.4), entry(b, 0.3), entry(x1, 0), entry(x2, 1))
        }, model.getIteracoes().get(0).getLines());
        assertEquals(new Variavel("b"), model.getIteracoes().get(0).getEntraNaBase());
        assertEquals(new Variavel("x1"), model.getIteracoes().get(0).getSaiDaBase());
        // Iteração 0 (Normalização)
        assertEquals(new Variavel[]{x0, a, b, x1, x2}, model.getIteracoes().get(1).getVariables());
        assertEquals(new SimplexTableauLine[]{
//            new SimplexTableauLine(x0, 0, entry(x0, 1), entry(a, -0.2), entry(b, -0.3), entry(x1, 0), entry(x2, 0)),
            new SimplexTableauLine(b, 35, entry(x0, 0), entry(a, 0.5), entry(b, 1), entry(x1, 2.5), entry(x2, 0)),
//            new SimplexTableauLine(x2, 18, entry(x0, 0), entry(a, 0.4), entry(b, 0.3), entry(x1, 0), entry(x2, 1))
        }, model.getIteracoes().get(1).getLines());
//        assertEquals(new Variavel("b"), model.getIteracoes().get(1).getEntraNaBase());
//        assertEquals(new Variavel("x1"), model.getIteracoes().get(1).getSaiDaBase());

    }

    /**
     * Cria uma entry.
     *
     * @param variavel
     * @param valor
     * @return {@code Map.Entry<Variavel, Double>}
     */
    public Map.Entry<Variavel, Double> entry(Variavel variavel, double valor) {
        return new AbstractMap.SimpleEntry<>(variavel, valor);
    }

}
