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
public class FuenteVO implements Serializable {
    private Short cdgFuente;
    
    private String descripcion;

    public Short getCdgFuente() {
        return cdgFuente;
    }

    public void setCdgFuente(Short cdgFuente) {
        this.cdgFuente = cdgFuente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
