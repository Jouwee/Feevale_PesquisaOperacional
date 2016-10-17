package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.commons.javafx.JFX;
import com.jouwee.commons.javafx.JFXClass;
import com.jouwee.commons.javafx.control.ExpressionLabel;
import com.jouwee.commons.mvc.PropertyEvent;
import com.jouwee.po.model.ModeloProblemaLinear;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Panel do modelo original
 *
 * @author Nícolas Pohren
 */
public class PanelModeloOriginal extends JavaFXView<ModeloProblemaLinear> implements JFXClass {

    /** Label do objetivo da função objetivo */
    private Label labelFoObjetivo;
    /** Label da expressão da função objetivo */
    private ExpressionLabel labelFoExpressao;

    /**
     * Cria o painel do modelo original
     *
     * @param model
     */
    public PanelModeloOriginal(ModeloProblemaLinear model) {
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
            updateLabelFoObjetivo();
            updateLabelFoExpressao();
        });
    }

    /**
     * Inicializa a interface
     */
    private void initGui() {
        setCenter(buildPanel());
    }

    /**
     * Cria o panel principal
     *
     * @return Node
     */
    private Node buildPanel() {
        VBox panel = new VBox();
        panel.getChildren().add(JFX.styleClass(new Label("Modelo original"), H2));
        panel.getChildren().add(JFX.styleClass(new Label("Função objetivo"), H3));
        panel.getChildren().add(JFX.styleClass(buildFuncaoObjetivo(), P));
        return panel;
    }

    /**
     * Cria o painél com a função objetivo
     *
     * @return Node
     */
    private Node buildFuncaoObjetivo() {
        HBox panel = new HBox();
        panel.getChildren().add(buildLabelFoObjetivo());
        panel.getChildren().add(new Label("("));
        panel.getChildren().add(buildLabelFoExpressao());
        panel.getChildren().add(new Label(")"));
        return panel;
    }

    /**
     * Cria o label da função objetivo
     *
     * @return Node
     */
    private Node buildLabelFoObjetivo() {
        labelFoObjetivo = new Label();
        updateLabelFoObjetivo();
        return labelFoObjetivo;
    }

    /**
     * Atualiza o valor do label de objetivo
     */
    private void updateLabelFoObjetivo() {
        labelFoObjetivo.setText(getModel().getFuncaoObjetivo().getObjetivo().toString());
    }

    /**
     * Cria o label da função objetivo
     *
     * @return Node
     */
    private Node buildLabelFoExpressao() {
        labelFoExpressao = new ExpressionLabel(getModel().getFuncaoObjetivo().getEquacao());
        updateLabelFoExpressao();
        return labelFoExpressao;
    }

    /**
     * Atualiza o valor do label de objetivo
     */
    private void updateLabelFoExpressao() {
        labelFoExpressao.setValue(getModel().getFuncaoObjetivo().getEquacao());
    }

}
