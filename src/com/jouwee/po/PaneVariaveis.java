package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.commons.javafx.Glyphicons;
import com.jouwee.commons.javafx.JFX;
import static com.jouwee.commons.javafx.JFXClass.GLYPH;
import static com.jouwee.commons.javafx.JFXClass.H2;
import static com.jouwee.commons.javafx.JFXClass.HEADER;
import static com.jouwee.commons.javafx.JFXClass.TABLE;
import com.jouwee.commons.mvc.PropertyEvent;
import com.jouwee.po.model.Variaveis;
import com.jouwee.po.model.Variavel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Painel de variáveis
 * 
 * @author Nícolas Pohren
 */
class PaneVariaveis extends JavaFXView<Variaveis> {

    /** Painel das variáveis */
    private GridPane panelVariaveis;

    /**
     * Cria o painel de variáveis
     * 
     * @param variaveis 
     */
    public PaneVariaveis(Variaveis variaveis) {
        super(variaveis);
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
            if (evt.getPropertyName().equals("variaveis")) {
                updatePaneVariaveis();
            }
        });
        updatePaneVariaveis();
    }

    /**
     * Inicializa a interface
     */
    private void initGui() {
        setTop(JFX.styleClass(new Label("Variáveis"), H2));
        setCenter(buildVariaveis());
        setPadding(new Insets(0, 10, 0, 10));
    }

    /**
     * Cria o painel das variáveis
     * 
     * @return Node
     */
    private Node buildVariaveis() {
        panelVariaveis = new GridPane();
        updatePaneVariaveis();
        return JFX.styleClass(panelVariaveis, TABLE);
    }
    
    /**
     * Atualiza o painel de variáveis
     */
    private void updatePaneVariaveis() {
        int linha = 0;
        panelVariaveis.getChildren().clear();
        panelVariaveis.addRow(linha++, JFX.styleClass(getHeaderRow(), HEADER));
        for (Variavel variavel : getModel().getVariaveis()) {
            panelVariaveis.addRow(linha++, buildRowVariavel(variavel));
        }
        panelVariaveis.add(buildAddVariavelButton(), 0, linha++, 3, 1);
    }

    /**
     * Retorna o cabeçalho
     * 
     * @return Node[]
     */
    private static Node[] getHeaderRow() {
        return new Node [] {
            new Label("Nome"), 
            new Label("Descrição"),
            new Label(""),
        };
    }

    /**
     * Cria uma linha para a variável
     * 
     * @param variavel
     * @return Node[]
     */
    private Node[] buildRowVariavel(Variavel variavel) {
        return new Node[] {
            buildFieldNome(variavel),
            buildFieldDescricao(variavel),
            buildButtonDelete(variavel)
        };
    }

    /**
     * Cria o field de nome
     * 
     * @param variavel
     * @return Node
     */
    private Node buildFieldNome(Variavel variavel) {
        TextField field = new TextField(variavel.getName());
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            variavel.setName(newValue);
        });
        return field;
    }

    /**
     * Cria o field de descrição
     * 
     * @param variavel
     * @return Node
     */
    private Node buildFieldDescricao(Variavel variavel) {
        TextField field = new TextField(variavel.getDescricao());
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            variavel.setDescricao(newValue);
        });
        return field;
    }

    /**
     * Cria o botão de deletar a variável
     * 
     * @param variavel
     * @return Node
     */
    private Node buildButtonDelete(Variavel variavel) {
        Button button = new Button(Glyphicons.TRASH_CAN);
        button.setOnAction((ActionEvent e) -> {
            getModel().remove(variavel);
        });
        return JFX.styleClass(button, GLYPH);
    }

    /**
     * Adiciona o botão de adicionar variável
     * 
     * @return Node
     */
    private Node buildAddVariavelButton() {
        Button button = new Button("Incluir variável");
        button.setOnAction((ActionEvent e) -> {
            getModel().add(new Variavel());
        });
        return button;
    }

}
