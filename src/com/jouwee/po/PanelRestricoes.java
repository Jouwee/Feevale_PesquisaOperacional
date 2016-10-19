package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.commons.javafx.JFX;
import static com.jouwee.commons.javafx.JFXClass.H2;
import com.jouwee.commons.javafx.ValueEvent;
import com.jouwee.commons.javafx.control.EquationField;
import com.jouwee.commons.mvc.PropertyEvent;
import com.jouwee.po.model.ModeloProblemaLinear;
import com.jouwee.po.model.Restricao;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * Painel de restrições
 * 
 * @author Nícolas Pohren
 */
public class PanelRestricoes extends JavaFXView<ModeloProblemaLinear> {

    /** Painel das restrições */
    private GridPane panelRestricoes;
    
    /**
     * Cria um novo painel de restrições
     * 
     * @param model 
     */
    public PanelRestricoes(ModeloProblemaLinear model) {
        super(model);
        initGui();
        addEventHandler(ModelEvent.MODEL_CHANGED, (ModelEvent event) -> {
            if (event.getTarget() == this) {
                inicializaModel();
            }
        });
        inicializaModel();
    }

    /**
     * Inicializa o modelo
     */
    private void inicializaModel() {
        getModel().addEventListener((PropertyEvent evt) -> {
            if (evt.getPropertyName().equals("restricoes")) {
                updatePaneRestricoes();
            }
        });
        updatePaneRestricoes();
    }

    /**
     * Inicializa a interface
     */
    private void initGui() {
        setTop(JFX.styleClass(new Label("Restrições"), H2));
        setCenter(buildRestricoes());
        setPadding(new Insets(0, 10, 0, 10));
    }

    /**
     * Cria o painel das restrições
     * 
     * @return Node
     */
    private Node buildRestricoes() {
        panelRestricoes = new GridPane();
        updatePaneRestricoes();
        return JFX.styleClass(panelRestricoes, TABLE);
    }
    
    /**
     * Atualiza o painel de restrições
     */
    private void updatePaneRestricoes() {
        int linha = 0;
        panelRestricoes.getChildren().clear();
        panelRestricoes.addRow(linha++, JFX.styleClass(getHeaderRow(), HEADER));
        for (Restricao restricao : getModel().getRestricoes()) {
            panelRestricoes.addRow(linha++, buildRowRestricao(restricao));
        }
    }

    /**
     * Returns the header row
     * 
     * @return Node[]
     */
    private static Node[] getHeaderRow() {
        return new Node [] {
            new Label("Nome"), 
            new Label("Equação")
        };
    }

    /**
     * Cria uma linha para a restrição
     * 
     * @param restricao
     * @return Node[]
     */
    private Node[] buildRowRestricao(Restricao restricao) {
        return new Node[] {
            buildFieldDescricao(restricao),
            buildFieldEquacao(restricao)
        };
    }

    /**
     * Cria o field de descricao
     * 
     * @param restricao
     * @return Node
     */
    private Node buildFieldDescricao(Restricao restricao) {
        TextField field = new TextField(restricao.getDescricao());
        field.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            restricao.setDescricao(field.getText());
        });
        return field;
    }

    /**
     * Cria o field de descricao
     * 
     * @param restricao
     * @return Node
     */
    private Node buildFieldEquacao(Restricao restricao) {
        EquationField field = new EquationField(restricao.getEquacao());
        field.addEventHandler(ValueEvent.TYPE, (ValueEvent event) -> {
            restricao.setEquacao(field.getValue());
        });
        return field;
    }

}
