
package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.commons.javafx.JFX;
import com.jouwee.commons.javafx.control.EquationLabel;
import com.jouwee.commons.math.AbsoluteValueNode;
import com.jouwee.commons.math.DifferenceNode;
import com.jouwee.commons.math.Equation;
import com.jouwee.commons.math.EquationType;
import com.jouwee.commons.math.ExpressionNode;
import com.jouwee.commons.math.MultiplicationNode;
import com.jouwee.commons.math.SumNode;
import com.jouwee.commons.math.VariableNode;
import com.jouwee.commons.mvc.PropertyEvent;
import com.jouwee.po.model.SimplexTableauLine;
import com.jouwee.po.model.SimplexTableauModel;
import com.jouwee.po.model.Variavel;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Nícolas Pohren
 */
public class PanelNormalizacao extends JavaFXView<SimplexTableauModel> {

    /** Campo para a função objetivo normalizada */
    private EquationLabel fieldFuncaoObjetivo;
    
    /**
     * Cria um novo painel de normalização
     * 
     * @param model 
     */
    public PanelNormalizacao(SimplexTableauModel model) {
        super(model);
        initGui();
        addEventHandler(ModelEvent.MODEL_CHANGED, (ModelEvent event) -> {
            if (event.getTarget() == this) {
                registraListeners();
            }
        });
        registraListeners();
    }

    /**
     * Registra os listeners
     */
    private void registraListeners() {
        getModel().addChildEventListener((PropertyEvent propertyEvent) -> {
            updateFieldFuncaoObjetivo();
        });
        updateFieldFuncaoObjetivo();
    }

    /**
     * Inicializa a interface
     */
    private void initGui() {
        setCenter(buildPane());
    }

    /**
     * Cria o painel interno
     * 
     * @return Node
     */
    private Node buildPane() {
        VBox pane = new VBox();
        pane.getChildren().add(JFX.styleClass(new Label("Normalizacao"), H2));
        pane.getChildren().add(JFX.styleClass(new Label("Função objetivo"), H3));
        pane.getChildren().add(JFX.styleClass(buildFuncaoObjetivo(), P));
        return pane;
    }
    
    /**
     * Cria a função objetivo
     * 
     * @return Node
     */
    private Node buildFuncaoObjetivo() {
        fieldFuncaoObjetivo = new EquationLabel();
        updateFieldFuncaoObjetivo();
        return fieldFuncaoObjetivo;
    }
    
    /**
     * Atualiza o campo da função objetivo normalizada
     */
    private void updateFieldFuncaoObjetivo() {
        SimplexTableauLine funcaoObjetivo = getModel().getLineFuncaoObjetivo();
        ExpressionNode lastNode = null;
        for (Map.Entry<Variavel, Double> entry : funcaoObjetivo.getCoeficientes().entrySet()) {
            if (entry.getKey() == null) {
                continue;
            }
            if (lastNode == null) {
                lastNode = new MultiplicationNode(new AbsoluteValueNode(Math.abs(entry.getValue())), new VariableNode(entry.getKey().getName()));
            } else {
                if (entry.getValue() < 0) {
                    lastNode = new DifferenceNode(lastNode, new MultiplicationNode(new AbsoluteValueNode(Math.abs(entry.getValue())), new VariableNode(entry.getKey().getName())));
                } else {
                    lastNode = new SumNode(lastNode, new MultiplicationNode(new AbsoluteValueNode(Math.abs(entry.getValue())), new VariableNode(entry.getKey().getName())));
                }
            }
        }
        ExpressionNode rightExpression = new AbsoluteValueNode(funcaoObjetivo.getValor());
        Equation equation = new Equation(lastNode, rightExpression, EquationType.EQUALS_TO);
        fieldFuncaoObjetivo.setValue(equation);
    }

}
