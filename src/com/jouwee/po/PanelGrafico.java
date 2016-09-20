package com.jouwee.po;

import com.jouwee.commons.application.JavaFXView;
import com.jouwee.commons.chart.CartesianPlane;
import com.jouwee.commons.chart.FunctionRenderingBean;
import com.jouwee.po.model.ResolucaoGraficaModel;
import com.jouwee.po.model.Restricao;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * Panel do gráfico
 * 
 * @author Jouwee
 */
public class PanelGrafico extends JavaFXView<ResolucaoGraficaModel> {

    /**
     * Cria novo panel do gráfico
     * 
     * @param model 
     */
    public PanelGrafico(ResolucaoGraficaModel model) {
        super(model);
        
        CartesianPlane cartesianPlane = new CartesianPlane();
        for (Restricao restricao : model.getModeloProblema().getRestricao()) {
            cartesianPlane.add(new FunctionRenderingBean(restricao.getEquacao().getLeftFunction()));
        }
        setCenter(cartesianPlane);
        setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(1), BorderWidths.DEFAULT)));
        
    }
    
}
