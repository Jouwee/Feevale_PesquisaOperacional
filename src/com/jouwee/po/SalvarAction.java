
package com.jouwee.po;

import com.google.gson.GsonBuilder;
import com.jouwee.commons.application.Action;
import com.jouwee.commons.application.ActionEvent;
import com.jouwee.commons.math.ExpressionNode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import javafx.stage.FileChooser;

/**
 *
 * @author Nícolas Pohren
 */
public class SalvarAction extends Action {

    /**
     * Cria uma ação de abrir exemplo
     */
    public SalvarAction() {
        super("Salvar");
    }

    @Override
    public void performed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Arquivos JSimplex", "*.jsimplex"),
                new FileChooser.ExtensionFilter("Arquivos JSon", "*.json"),
                new FileChooser.ExtensionFilter("Todos os arquivos", "*.*")
            );
        File file = fileChooser.showSaveDialog(Aplicacao.get().getStage());
        if (file != null) {
            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file))) {
                new GsonBuilder().registerTypeAdapter(ExpressionNode.class, new InterfaceAdapter<>()).create().toJson(Aplicacao.get().getModel(), writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
