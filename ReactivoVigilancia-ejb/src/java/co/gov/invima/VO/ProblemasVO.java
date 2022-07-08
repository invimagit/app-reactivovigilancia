/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.VO;

import java.io.Serializable;

/**
 *
 * @author mgualdrond
 */
public class ProblemasVO implements Serializable {
    
    private Integer cdgProblema;

    private String descripcion;

    public Integer getCdgProblema() {
        return cdgProblema;
    }

    public void setCdgProblema(Integer cdgProblema) {
        this.cdgProblema = cdgProblema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
