package com.jouwee.po.model;

import com.jouwee.commons.mvc.Model;

/**
 * Enunciado de uma questão
 * 
 * @author Nícolas Pohren
 */
public class Enunciado implements Model {
    
    /** Conteúdo HTML */
    private String htmlContent;

    /**
     * Cria um novo enunciado
     */
    public Enunciado() {
        htmlContent = "<html></html>";
    }

    /**
     * Retorna o conteúdo HTML
     * 
     * @return String
     */
    public String getHtmlContent() {
        return htmlContent;
    }

    /**
     * Define o conteúdo HTML
     * 
     * @param htmlContent 
     */
    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
    
}
