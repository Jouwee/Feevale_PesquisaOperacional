
package com.jouwee.po;

import com.google.gson.GsonBuilder;
import com.jouwee.commons.application.Action;
import com.jouwee.commons.application.ActionEvent;
import com.jouwee.commons.math.ExpressionNode;
import com.jouwee.po.model.SimplexModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javafx.stage.FileChooser;

/**
 *
 * @author Nícolas Pohren
 */
public class AbrirAction extends Action {

    /**
     * Cria uma ação de abrir exemplo
     */
    public AbrirAction() {
        super("Abrir");
    }

    @Override
    public void performed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Arquivos JSimplex", "*.jsimplex"),
                new FileChooser.ExtensionFilter("Arquivos JSon", "*.json"),
                new FileChooser.ExtensionFilter("Todos os arquivos", "*.*")
            );
        File file = fileChooser.showOpenDialog(Aplicacao.get().getStage());
        if (file != null) {
            try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file))) {
                SimplexModel model = new GsonBuilder().registerTypeAdapter(ExpressionNode.class, new InterfaceAdapter<>()).create().fromJson(reader, SimplexModel.class);
                Aplicacao.get().setModel(model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
