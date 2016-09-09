package com.jouwee.po.model;

/**
 * Enunciado de uma questão
 * 
 * @author Nícolas Pohren
 */
public class Enunciado {
    
    /** Conteúdo HTML */
    private String htmlContent;

    /**
     * Cria um novo enunciado
     */
    public Enunciado() {
        htmlContent = "<html>pinto <b>pinto grosso</b></html>";
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
