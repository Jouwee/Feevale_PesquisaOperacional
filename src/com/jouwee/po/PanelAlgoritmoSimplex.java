package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.commons.javafx.JFX;
import static com.jouwee.commons.javafx.JFXClass.PANEL;
import com.jouwee.commons.mvc.PropertyEvent;
import com.jouwee.po.model.SimplexModel;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Panel do algoritmo Simplex
 *
 * @author Nícolas Pohren
 */
public class PanelAlgoritmoSimplex extends JavaFXView<SimplexModel> {

    /** Panel iterações simplex */
    private PanelModeloOriginal panelOriginal;
    /** Panel do modelo */
    private PanelNormalizacao panelNormalizacao;
    /** Panel iterações simplex */
    private PanelIteracoesSimplex panelIteracoes;
    /** Panel de resultado */
    private PanelResultado panelResultado;
    /** Label do erro de validação */
    private Label erroValidacao;

    /**
     * Cria o panel do algoritmo simplex
     *
     * @param model
     */
    public PanelAlgoritmoSimplex(SimplexModel model) {
        super(model);
        initGui();
        addEventHandler(ModelEvent.MODEL_CHANGED, (ModelEvent event) -> {
            if (event.getTarget() == this) {
                registraListeners();
            }
        });
    }

    /**
     * Regitra os listeners
     */
    private void registraListeners() {
        getModel().addEventListener((PropertyEvent propertyEvent) -> {
                if (propertyEvent.getPropertyName().equals("erroValidacao")) {
                    Platform.runLater(() -> {
                        if (getModel().hasErroValidacao()) {
                            erroValidacao.setVisible(true);
                            erroValidacao.setText(getModel().getErroValidacao());
                            panelOriginal.setVisible(false);
                            panelNormalizacao.setVisible(false);
                            panelIteracoes.setVisible(false);
                            panelResultado.setVisible(false);
                            panelNormalizacao.setVisible(false);
                        } else {
                            erroValidacao.setVisible(false);
                            panelOriginal.setVisible(true);
                            panelNormalizacao.setVisible(true);
                            panelIteracoes.setVisible(true);
                            panelResultado.setVisible(true);
                            panelNormalizacao.setVisible(true);
                        }
                    });
                }
            });
        panelOriginal.setModel(getModel().getModeloProblema());
        panelNormalizacao.setModel(getModel().getEtapaNormalizacao());
        panelIteracoes.setModel(getModel());
        panelResultado.setModel(getModel());
        getModel().addEventListener((PropertyEvent propertyEvent) -> {
            if (propertyEvent.getPropertyName().equals("iteracoes")) {
                panelNormalizacao.setModel(getModel().getEtapaNormalizacao());
            }
        });
    }

    /**
     * Inicializa a interface
     */
    public void initGui() {
        ScrollPane scroll = new ScrollPane(buildPanel()) {
            @Override
            public void requestFocus() {
            }
        };
        scroll.setFocusTraversable(false);
        setCenter(scroll);
        JFX.styleClass(this, PANEL);
    }

    /**
     * Cria o painel principal
     *
     * @return
     */
    public VBox buildPanel() {
        VBox panel = new VBox();
        panel.getChildren().add(buildPanelErroValidacao());
        panel.getChildren().add(buildPanelResultado());
        panel.getChildren().add(buildPanelModeloOriginal());
        panel.getChildren().add(buildPanelNormalizacao());
        panel.getChildren().add(buildPanelIteracoesSimplex());
        return panel;
    }

    /**
     * Cria o painel de erro de validação
     * 
     * @return Node
     */
    private Node buildPanelErroValidacao() {
        erroValidacao = new Label();
        erroValidacao.setStyle("-fx-text-fill: red; -fx-font-weight:bold; -fx-font-size: 14pt");
        return erroValidacao;
    }
    
    /**
     * Cria o painel de iterações
     *
     * @return JavaFXView
     */
    private JavaFXView buildPanelIteracoesSimplex() {
        panelIteracoes = new PanelIteracoesSimplex(getModel());
        return panelIteracoes;
    }

    /**
     * Cria o painel de resultado
     *
     * @return JavaFXView
     */
    private JavaFXView buildPanelResultado() {
        panelResultado = new PanelResultado(getModel());
        return panelResultado;
    }

    /**
     * Cria o painel do modelo inicial
     *
     * @return JavaFXView
     */
    private JavaFXView buildPanelModeloOriginal() {
        panelOriginal = new PanelModeloOriginal(getModel().getModeloProblema());
        return panelOriginal;
    }

    /**
     * Cria o painel de normalização
     *
     * @return JavaFXView
     */
    private JavaFXView buildPanelNormalizacao() {
        panelNormalizacao = new PanelNormalizacao(getModel().getEtapaNormalizacao());
        return panelNormalizacao;
    }

}
