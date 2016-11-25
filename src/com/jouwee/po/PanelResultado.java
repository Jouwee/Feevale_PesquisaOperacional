
package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.application.ModelEvent;
import com.jouwee.commons.javafx.JFX;
import com.jouwee.commons.javafx.JFXClass;
import com.jouwee.po.model.Restricao;
import com.jouwee.po.model.SimplexModel;
import com.jouwee.po.model.Variavel;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Cria o painel de resultado
 * 
 * @author Nícolas Pohren
 */
public class PanelResultado extends JavaFXView<SimplexModel> {

    /** Formatador numérico */
    private final NumberFormat formatter;
    /** Painel contendo o resultado */
    private GridPane pane;
    
    /**
     * Painel de resultado
     * 
     * @param model 
     */
    public PanelResultado(SimplexModel model) {
        super(model);
        formatter = new DecimalFormat("#0.00");
        initGui();
        addEventHandler(ModelEvent.MODEL_CHANGED, (ModelEvent event) -> {
            if (event.getTarget() == this) {
                initializeModel();
            }
        });
        initializeModel();
    }
    
    /**
     * Inicializa o modelo
     */
    private void initializeModel() {
        getModel().addListenerIteracoes((Observable observable) -> {
            updatePanel();
        });
        updatePanel();
    }

    /**
     * Inicializa a interface
     */
    private void initGui() {
        setTop(JFX.styleClass(new Label("Resultado"), JFXClass.H2));
        setCenter(JFX.styleClass(buildResultado(), TABLE));
    }

    /**
     * Cria o resultado
     * 
     * @return Node
     */
    private Node buildResultado() {
        pane = new GridPane();
        updatePanel();
        return pane;
    }
    
    /**
     * Atualiza o panel
     */
    private void updatePanel() {
        Platform.runLater(() -> {
            pane.getChildren().clear();
            int index = 0;
            index = updateFuncaoObjetivo(index);
            index = updateVariaveis(index);
            index = updateRestricoes(index);
        });
    }

    /**
     * Atualiza a função objetivo
     * 
     * @param index
     * @return int
     */
    private int updateFuncaoObjetivo(int index) {
        pane.addRow(index++, JFX.styleClass(new Label("Função objetivo"), HEADER), new Label(formatter.format(getModel().getIteracaoFinal().getValorFuncaoObjetivo())));
        return index;
    }

    /**
     * Atualiza as variáveis
     * 
     * @param index
     * @return int
     */
    private int updateVariaveis(int index) {
        for (Variavel variavel : getModel().getModeloProblema().getVariaveis().getVariaveis()) {
            pane.addRow(index++, JFX.styleClass(new Label(variavel.getDescricao()), HEADER), new Label(formatter.format(getModel().getIteracaoFinal().getValor(variavel))));
        }
        return index;
    }

    /**
     * Atualiza as restrições
     * 
     * @param index
     * @return int
     */
    private int updateRestricoes(int index) {
        for (Restricao restricao : getModel().getModeloProblema().getRestricoes()) {
            pane.addRow(index++, JFX.styleClass(new Label("Folga \"" + restricao.getDescricao() + "\""), HEADER), new Label(formatter.format(getModel().getIteracaoFinal().getFolga(restricao))));
        }
        return index;
    }
    
}
