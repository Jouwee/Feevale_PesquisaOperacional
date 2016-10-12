package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.javafx.JFX;
import com.jouwee.commons.javafx.JFXClass;
import com.jouwee.commons.javafx.control.ExpressionField;
import com.jouwee.po.model.FuncaoObjetivo;
import com.jouwee.po.model.Objetivo;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Painel da função objetivo
 * 
 * @author Nícolas Pohren
 */
class PaneFuncaoObjetivo extends JavaFXView<FuncaoObjetivo> implements JFXClass {

    /**
     * Cria o painel de edição da função objetivo 
     * 
     * @param funcaoObjetivo 
     */
    public PaneFuncaoObjetivo(FuncaoObjetivo funcaoObjetivo) {
        super(funcaoObjetivo);
        initGui();
    }

    /**
     * Inicializa a interface
     */
    private void initGui() {
        setTop(JFX.styleClass(new Label("Função objetivo"), H2));
        setCenter(buildFuncaoObjetivo());
        setPadding(new Insets(10, 10, 10, 10));
    }

    /**
     * Cria o painel da função objetivo
     * 
     * @return Node
     */
    private Node buildFuncaoObjetivo() {
        BorderPane pane = new BorderPane();
        pane.setLeft(buildTipoFuncaoField());
        pane.setCenter(buildEquacaoFuncaoField());
        BorderPane.setMargin(pane.getLeft(), new Insets(0, 10, 0, 0));
        return pane;
    }

    /**
     * Cria o campo para o tipo de função objetivo
     * 
     * @return Node
     */
    private Node buildTipoFuncaoField() {
        ComboBox<Objetivo> comboObjetivo = new ComboBox<>(FXCollections.observableArrayList(Objetivo.values()));
        comboObjetivo.setValue(Objetivo.MAXIMIZAR);
        return comboObjetivo;
    }

    /**
     * Cria o campo para digitação da função objetivo
     * 
     * @return Node
     */
    private Node buildEquacaoFuncaoField() {
        return new ExpressionField();
    }

}
