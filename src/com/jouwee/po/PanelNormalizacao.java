
package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.commons.javafx.JFX;
import static com.jouwee.commons.javafx.JFXClass.B;
import static com.jouwee.commons.javafx.JFXClass.HEADER;
import static com.jouwee.commons.javafx.JFXClass.TABLE;
import com.jouwee.commons.javafx.control.EquationLabel;
import com.jouwee.commons.mvc.PropertyEvent;
import com.jouwee.po.model.SimplexTableauLine;
import com.jouwee.po.model.SimplexTableauModel;
import com.jouwee.po.model.Variavel;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Nícolas Pohren
 */
public class PanelNormalizacao extends JavaFXView<SimplexTableauModel> {

    /** Campo para a função objetivo normalizada */
    private EquationLabel fieldFuncaoObjetivo;
    /** Panel variáveis */
    private GridPane panelVariaveis;
    /** Panel de restrições */
    private GridPane panelRestricoes;
    
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
            updatePanelVariaveis();
            updatePanelRestricoes();
        });
        updateFieldFuncaoObjetivo();
        updatePanelVariaveis();
        updatePanelRestricoes();
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
        pane.getChildren().add(JFX.styleClass(new Label("Normalização"), H2));
        pane.getChildren().add(JFX.styleClass(new Label("Variáveis criadas"), H3));
        pane.getChildren().add(JFX.styleClass(buildVariaveisCriadas(), P));
        pane.getChildren().add(JFX.styleClass(new Label("Função objetivo"), H3));
        pane.getChildren().add(JFX.styleClass(buildFuncaoObjetivo(), P));
        pane.getChildren().add(JFX.styleClass(new Label("Restrições"), H3));
        pane.getChildren().add(JFX.styleClass(buildPanelRestricoes(), P));
        return pane;
    }
    
    /**
     * Cria o painel de variáveis
     * 
     * @return Node
     */
    private Node buildVariaveisCriadas() {
        panelVariaveis = new GridPane();
        updatePanelVariaveis();
        return JFX.styleClass(panelVariaveis, TABLE);
    }

    /**
     * Atualiza o painel das variáveis
     */
    private void updatePanelVariaveis() {
        Platform.runLater(() -> {
            int linha = 0;
            panelVariaveis.getChildren().clear();
            panelVariaveis.addRow(linha++, JFX.styleClass(new Node[]{
                new Label("Nome"),
                new Label("Descrição")
            }, HEADER));
            for (Variavel variavel : getModel().getVariaveisAuxiliares()) {
                panelVariaveis.addRow(linha++, JFX.styleClass(new Label(variavel.getName()), B), new Label(variavel.getDescricao()));
            }
        });
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
        Platform.runLater(() -> {
            fieldFuncaoObjetivo.setValue(getModel().getLineFuncaoObjetivo().getEquation());
        });
    }
    
    /**
     * Cria o painel de restrições
     * 
     * @return Node
     */
    private Node buildPanelRestricoes() {
        panelRestricoes = new GridPane();
        updatePanelRestricoes();
        return JFX.styleClass(panelRestricoes, TABLE);
    }

    /**
     * Atualiza o painel das restricoes
     */
    private void updatePanelRestricoes() {
        Platform.runLater(() -> {
            int linha = 0;
            panelRestricoes.getChildren().clear();
            panelRestricoes.addRow(linha++, JFX.styleClass(new Node[]{
                new Label("Nome"),
                new Label("Equacao")
            }, HEADER));
            for (SimplexTableauLine line : getModel().getLines()) {
                if (line.getVariavel().getRestricaoFolga() != null) {
                    panelRestricoes.addRow(linha++, JFX.styleClass(new Node[]{
                        new Label(line.getVariavel().getRestricaoFolga().getDescricao()),
                        new EquationLabel(line.getEquation())
                    }));
                }
            }
        });
    }

}
