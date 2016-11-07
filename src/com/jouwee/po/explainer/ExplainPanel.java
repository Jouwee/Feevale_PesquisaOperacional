package com.jouwee.po.explainer;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.po.model.SimplexModel;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

/**
 * Painél de explicações
 *
 * @author Nícolas Pohren
 */
public class ExplainPanel extends JavaFXView<SimplexModel> {

    /**
     * Explicação
     */
    private WebView explicacao;

    /**
     * Cria o painel de explicações
     *
     * @param model
     */
    public ExplainPanel(SimplexModel model) {
        super(model);
        initGui();
        setVisible(false);
    }

    /**
     * Inicializa a interface
     */
    private void initGui() {
        getStylesheets().add(ExplainPanel.class.getResource("ExplainPanel.css").toExternalForm());
        getStyleClass().add("explain-panel");
        setCenter(buildMainPanel());
    }

    /**
     * Cria o painél principal
     *
     * @return node
     */
    private Node buildMainPanel() {
        HBox pane = new HBox();
        pane.setAlignment(Pos.CENTER);
        BorderPane dialog = buildDialog();
//        dialog.setLayoutX(100);
//        dialog.setLayoutY(100);
        dialog.setPrefSize(600, 400);
        dialog.setMaxSize(600, 400);
//        dialog.set
        pane.getChildren().add(dialog);
        return pane;
    }

    /**
     * Cria o diálogo
     *
     * @return Node
     */
    private BorderPane buildDialog() {
        BorderPane pane = new BorderPane();
        pane.getStyleClass().add("dialog");
        pane.setTop(buildHeader());
        pane.setCenter(buildBody());
        pane.setEffect(buildShadow());
        return pane;
    }

    /**
     * Cria o header
     *
     * @return Node
     */
    private Node buildHeader() {
        BorderPane header = new BorderPane();
        header.getStyleClass().add("header");
        header.setCenter(new Label("Explicação"));
        Button close = new Button("x");
        close.addEventHandler(ActionEvent.ACTION, (e) -> {
            setVisible(false);
        });
        header.setRight(close);
        return header;
    }

    /**
     * Cria o corpo do painel
     *
     * @return Node
     */
    private Node buildBody() {
        explicacao = new WebView();
        explicacao.getEngine().setUserStyleSheetLocation(ExplainPanel.class.getResource("ExplainPanel.css").toExternalForm());
        return explicacao;
    }

    /**
     * Define a explicação
     *
     * @param explicacaoText
     */
    public void setExplicacao(String explicacaoText) {
        explicacao.getEngine().loadContent(explicacaoText);
    }

    /**
     * Cria o efeito de sombra
     *
     * @return Effect
     */
    private Effect buildShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.0, 0.0, 0.0, 0.5));
        return dropShadow;
    }

}
